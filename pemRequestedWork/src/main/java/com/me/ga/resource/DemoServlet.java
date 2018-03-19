/**
 * 
 */
package com.me.ga.resource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;

import com.me.ga.constants.Constants;
import com.me.ga.entity.Case;
import com.me.ga.entity.CaseForDis;
import com.me.ga.entity.CaseProcessInfo;
import com.me.ga.entity.Customer;
import com.me.ga.entity.ProcessResult;
import com.me.ga.mapper.CriteriaMapper;
import com.me.ga.service.ServiceImpl;
import com.me.ga.util.ExcelUtil;
import com.me.ga.util.LoggersUtil;

/**
 * @author GLAD.Author	
 * @version $Id$
 * @since JDK5.0
 */
@Controller
@RequestMapping(produces = "application/json;charset=utf-8")
public class DemoServlet
    implements ServletContextAware, ApplicationContextAware {

    ServletContext servletContext = null;

    ApplicationContext applicationContext = null;

    @RequestMapping("te")
    public void sayHello(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {

        WebApplicationContext applicationContext =
            WebApplicationContextUtils.getWebApplicationContext(request
                .getServletContext());
        CriteriaMapper session =
            (CriteriaMapper)applicationContext.getBean("criteriaMapper");

        List<Customer> customers = session.selectall$customerByKey();
        for (Customer customer : customers) {
            System.out.println(customer);
            response.getWriter().write(customer.toString());
        }
        //        request.getRequestDispatcher("/WEB-INF/page/hello.html").forward(
        //            request, response);
    }

    @RequestMapping("/go")
    public String web_init(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {

        //        request.getRequestDispatcher("/WEB-INF/page/hello.html").forward(
        //            request, response);
        //        System.out.println(request.getContextPath());
        //        response.sendRedirect(request.getContextPath()+"/WEB-INF/page/main.jsp");
        return "main";
    }

    @RequestMapping(value = "init")
    public @ResponseBody
    String init(HttpServletRequest request) throws ServletException {
        LoggersUtil.info(this.getClass(), "页面初期化开始");
        JSONObject data = null;

        if (!Constants.isExist) {
            // ----------------------------------------------------------------------------------------------------
            // Read From excel
            String location =
                servletContext.getRealPath("/upload") + "/TT.xlsx";
            ExcelUtil reader = null;
            try {
                System.out.println(location);
                reader = new ExcelUtil(location, "18_03");
            } catch (Exception e) {
                JSONObject error =
                    new JSONObject().put("ERROR", "初始化EXCEL文件不存在");
                return error.toString();
            }

            // Get Data according to Sheet Name
            LoggersUtil.info(this.getClass(), "EXCEL init");
            try {
                data = reader.parseSheet(reader.getTopName());
            } catch (IOException e) {
                JSONObject error =
                    new JSONObject().put("ERROR", e.getMessage());
                return error.toString();
            }
            Constants.isExist = true;
            data.put("EXIST", "true");

            // Keep Main Data for save
            request.getSession().setAttribute("data", data.get("data"));

            // Set Other Property
            initDataFromEXCEL(data);
        } else {
            // Get Data according to session
            LoggersUtil.info(this.getClass(), "DB init");
            ServiceImpl service =
                (ServiceImpl)applicationContext.getBean("service");
            JSONArray jsonArray = service.selectAll();
            if (jsonArray.length() == 0) {
                JSONObject error = new JSONObject().put("ERROR", "DB中没有数据");
                Constants.isExist = false;
                return error.toString();
            }
            data = new JSONObject().put("data", jsonArray);

            // Set Other Property
            initDataFromDB(data);
        }

        // Set Thead
        initData(data);

        //-----------------------------------------------------------------------------------------------------
        // Set Display Head
        //        Properties prop = new Properties();
        //        FileInputStream fis =
        //            new FileInputStream(this.getClass().getResource("/db_item_map.properties")
        //                .getFile());
        //        prop.load(fis);
        //        System.out.println(new JSONObject(prop));
        //        data.put(
        //            "head",
        //            "{'下流見積':12,'SEQNO':'82067','着手日':'17/12/12','納期遵守':'N','プロダクト種別':'QR-NET','批案件分类':'qrnet调查后改修','問題管理NO':'ACTM_MS1469','担当SE':'花原','上流見積':12,'希望納期':'17/12/22','プロダクトID':'QRS009','FDver':'3.14.0','依頼作業':'変更','仕様発行日':'17/12/07','优先顺':'','回答納期':'','担当PE':'馬澤昆'}");

        //-----------------------------------------------------------------------------------------------------
        return data.toString();
    }

    /**
     * 文件上传
     * @throws IOException 
     * @throws IllegalStateException 
     */
    @RequestMapping(value = "uploadEXCEL", method = RequestMethod.POST)
    public String uploadEXCEL(MultipartFile customFile) throws Exception {
        LoggersUtil.info(this.getClass(), "上传EXCEL开始");
        // 图片上传
        // 设置图片名称，不能重复，可以使用uuid
        String uploadFileName = UUID.randomUUID().toString();

        // 获取文件名
        String oriName = customFile.getOriginalFilename();
        // 获取图片后缀
        String extName = oriName.substring(oriName.lastIndexOf("."));

        // 开始上传
        System.out.println(servletContext.getRealPath("/upload"));

        File file =
            new File(servletContext.getRealPath("/upload"), "TT" + extName);
        if (file != null) {
            LoggersUtil.info(this.getClass(), "delete TT.xls" + file.delete());
        }
        customFile.transferTo(new File(servletContext.getRealPath("/upload"),
            "TT" + extName));
        Constants.isExist = false;
        return "redirect:/go";
    }

    /**
     * 从EXCEL保存到DB中
     */
    @RequestMapping(value = "saveAll", method = RequestMethod.POST)
    public @ResponseBody
    String saveAll(HttpServletRequest request) {
        ServiceImpl service =
            (ServiceImpl)applicationContext.getBean("service");
        JSONArray array = (JSONArray)request.getSession().getAttribute("data");
        try {
            service.saveAll(array);
        } catch (Exception e) {
            JSONObject error = new JSONObject().put("ERROR", "全存储失败 请联系管理员");
            return error.toString();
        }
        return array.toString();
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public @ResponseBody
    String update(@RequestBody
    CaseProcessInfo caseProcessInfo) throws Exception {
        ServiceImpl service =
            (ServiceImpl)applicationContext.getBean("service");
        try {
            service.update(caseProcessInfo);
        } catch (Exception e) {
            JSONObject error = new JSONObject().put("ERROR", "更新失败 请联系管理员");
            return error.toString();
        }
        return new JSONObject(new ProcessResult(true, "success")).toString();
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public @ResponseBody
    String search(@RequestBody
    CaseForDis caseForDis) throws Exception {
        ServiceImpl service =
            (ServiceImpl)applicationContext.getBean("service");
        JSONArray jsonArray = service.search(caseForDis);

        if (jsonArray.length() == 0) {
            JSONObject error = new JSONObject().put("ERROR", "DB中没有数据");
//            Constants.isExist = false;
            return error.toString();
        }

        JSONObject data = new JSONObject().put("data", jsonArray);

        // Set Other Property
        initDataFromDB(data);
        initData(data);

        return data.toString();
    }

    private void initData(JSONObject data) {
        data.put(
            "SCR_HEAD",
            "[\"SEQNO\",\"問題管理NO\",\"プロダクトID\",\"FDver\",\"依頼作業\",\"プロダクト種別\",\"仕様発行日\",\"希望納期\",\"納期遵守\",\"上流見積\",\"担当SE\",\"着手日\",\"担当PE\",\"回答納期\",\"下流見積\",\"优先顺\",\"大种别\",\"Review担当\",\"Status\",\"实际PE\",\"実際見積\",\"备注\"]");
        data.put(
            "SCR_SEARCH",
            "[\"SEQNO\",\"問題管理NO\",\"プロダクトID\",\"依頼作業\",\"プロダクト種別\",\"着手日\",\"担当PE\",\"回答納期\",\"大种别\",\"优先顺\",\"下流Status\",\"实际PE\"]");
        data.put("SCR_EDITED",
            "[\"担当PE\",\"下流見積\",\"下流Review担当\",\"実際見積\",\"大种别\",\"备注\",\"优先顺\",\"实际PE\"]");
        data.put("SCR_DATE_EDITED", "[\"着手日\",\"回答納期\"]");

        data.put(
            "FIELD_MAPPING",
            "{\"SEQNO\" : \"caseSeqno\",\"caseSeqno\" : \"caseSeqno\",\"問題管理NO\" : \"quNum\",\"quNum\" : \"quNum\",\"プロダクトID\" : \"projectId\",\"projectId\" : \"projectId\",\"FDver\" : \"fdVer\",\"fdVer\" : \"fdVer\",\"依頼作業\" : \"dealType\",\"dealType\" : \"dealType\", \"プロダクト種別\" : \"projectType\",\"projectType\" : \"projectType\", \"仕様発行日\" : \"specificationIssueDate\", \"specificationIssueDate\" : \"specificationIssueDate\", \"希望納期\" : \"desiredDeliveryDate\",\"desiredDeliveryDate\" : \"desiredDeliveryDate\", \"納期遵守\" : \"timeCompliance\",\"timeCompliance\" : \"timeCompliance\", \"上流見積\" : \"upEstimates\",\"upEstimates\" : \"upEstimates\", \"担当SE\" : \"seName\",\"seName\" : \"seName\",\"着手日\" : \"startDate\",\"startDate\" : \"startDate\", \"担当PE\" : \"downPe\",\"downPe\" : \"downPe\", \"回答納期\" : \"answerDeliveryDate\",\"answerDeliveryDate\" : \"answerDeliveryDate\", \"下流見積\" : \"downEstimates\",\"downEstimates\" : \"downEstimates\", \"备注\" : \"caseType\",\"caseType\" : \"caseType\",\"优先顺\" : \"importance\",\"importance\" : \"importance\",\"大种别\" : \"btype\",\"btype\" : \"btype\", \"Review担当\" : \"downReview\",\"downReview\" : \"downReview\",\"Status\" : \"downStatus\",\"downStatus\" : \"downStatus\",\"実際見積\" : \"downActualEstimates\", \"downActualEstimates\" : \"downActualEstimates\",\"实际PE\" : \"downActualPe\",\"downActualPe\" : \"downActualPe\"}");
    }

    private void initDataFromEXCEL(JSONObject data) {
        data.put(
            "SCR_ORDER",
            "[\"SEQNO\",\"問題管理NO\",\"プロダクトID\",\"FDver\",\"依頼作業\",\"プロダクト種別\",\"仕様発行日\",\"希望納期\",\"納期遵守\",\"上流見積\",\"担当SE\",\"着手日\",\"担当PE\",\"回答納期\",\"下流見積\",\"优先顺\",\"大种别\",\"Review担当\",\"Status\",\"实际PE\",\"実際見積\",\"备注\"]");
        data.put("SCR_SELECT", "[\"Status\"]");
        //        data.put("SCR_SELECT", "[\"优先顺\"，\"Status\"]");
        data.put("SCR_TRUNCATE", "[\"プロダクトID\",\"問題管理NO\",\"备注\"]");
        data.put("EDITED",
            "[\"担当PE\",\"下流見積\",\"下流Review担当\",\"実際見積\",\"大种别\",\"备注\",\"优先顺\",\"实际PE\"]");
        data.put("DATE_EDITED", "[\"着手日\",\"回答納期\"]");
    }

    private void initDataFromDB(JSONObject data) {
        data.put(
            "SCR_ORDER",
            "[\"caseSeqno\",\"quNum\",\"projectId\",\"fdVer\",\"dealType\",\"projectType\",\"specificationIssueDate\",\"desiredDeliveryDate\",\"timeCompliance\",\"upEstimates\",\"seName\",\"startDate\",\"downPe\",\"answerDeliveryDate\",\"downEstimates\",\"importance\",\"btype\",\"downReview\",\"downStatus\",\"downActualPe\",\"downActualEstimates\",\"caseType\"]");
        //        data.put("SCR_SELECT", "[\"importance\"，\"downStatus\"]");
        data.put("SCR_SELECT", "[\"downStatus\"]");
        data.put("SCR_TRUNCATE", "[\"projectId\",\"quNum\",\"caseType\"]");
        data.put(
            "EDITED",
            "[\"downPe\",\"downEstimates\",\"downReview\",\"downActualEstimates\",\"btype\",\"caseType\",\"importance\",\"downActualPe\"]");
        data.put("DATE_EDITED", "[\"startDate\",\"answerDeliveryDate\"]");
    }
    
    
    @RequestMapping("/saveCase")
    public String saveCase(Case vcase){
        ServiceImpl service =
            (ServiceImpl)applicationContext.getBean("service");
        try {
            service.saveCase(vcase);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/go";
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException {
        this.applicationContext = applicationContext;
    }
}
