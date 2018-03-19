/**
 * 
 */
package com.me.ga.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class ExcelUtil {

    private XSSFWorkbook xssfWorkbook;

    private String topName;

    //    ArrayList<JSONObject> jsonList = new ArrayList<JSONObject>();
    JSONArray jsonArray = new JSONArray();

    public ExcelUtil(String fileAddr, String topName) throws IOException {
        this.xssfWorkbook = new XSSFWorkbook(fileAddr);
        this.topName = topName;
    }

    ExcelUtil() {

    }

    // String location = "C:\\Users\\zhangxiao3\\Desktop\\abc.xlsx";

    @SuppressWarnings("static-access")
    public JSONObject parseSheet(String currentSheetName) throws IOException {
        XSSFSheet currentSheet = xssfWorkbook.getSheet(currentSheetName);

        // order map
        JSONObject jsonObj = new JSONObject();
        //        LinkedHashMap<String, Object> jsonObj = new LinkedHashMap<String, Object>();
        //        LinkedProperties jsonObj = new LinkedProperties();
        XSSFRow titleRow = currentSheet.getRow(0);
        int titleNum = titleRow.getLastCellNum();
        String[] titleList = new String[titleNum];
        for (int i = 0; i < titleNum; i++) {
            // Title
            if (titleRow.getCell(i) != null) {
                titleList[i] = titleRow.getCell(i).getStringCellValue();
            }
        }

        DataFormatter formatter = new DataFormatter(LocaleUtil.getUserLocale());
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
        int jsonArrCount = 0;
        for (int row = 1; row <= currentSheet.getLastRowNum(); row++) {
            XSSFRow tempRow = currentSheet.getRow(row);
            // order map
            jsonObj = new JSONObject();

            for (int cell = 1; cell < titleNum; cell++) {
                // Content
                XSSFCell tempCell = tempRow.getCell(cell);
                if (tempCell == null) {
                    jsonObj.put(titleList[cell], "");
                    continue;
                }

                // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
                String text = formatter.formatCellValue(tempCell);

                // Alternatively, get the value and format it yourself
                switch (tempCell.getCellType()) {
                    case 1:
                        jsonObj.put(titleList[cell],
                            tempCell.getStringCellValue() == ""
                                ? " "
                                : tempCell.getStringCellValue());
                        break;
                    case 0:
                        if (DateUtil.isCellDateFormatted(tempCell)) {
                            jsonObj.put(titleList[cell],
                                sdf.format(tempCell.getDateCellValue()));
                            //                            jsonObj.put(titleList[cell],
                            //                                formatter.formatCellValue(tempCell));
                        } else {
                            jsonObj.put(titleList[cell],
                                tempCell.getNumericCellValue());
                        }
                        break;
                    case 4:
                        jsonObj.put(titleList[cell],
                            tempCell.getBooleanCellValue());
                        break;
                    case 2:
                        jsonObj.put(titleList[cell], tempCell.getCellFormula());
                        break;
                    case 3:
                        jsonObj.put(titleList[cell], " ");
                        break;
                    default:
                }
            }

            // SEQNO check
            String pattern = "[0-9]{5}-[0-9]|[0-9]{5}";
            String content = "";
            if (Double.class.isInstance(jsonObj.get("SEQNO"))) {
                Double seq = (Double)jsonObj.get("SEQNO");
                String num = seq.toString();
                content = num.substring(0, num.indexOf("."));
                jsonObj.put("SEQNO", content);
            } else {
                content = (String)jsonObj.get("SEQNO");
            }
            if (Pattern.matches(pattern, content)) {
                //                System.out.println(jsonObj.toString());
                jsonArray.put(jsonArrCount, jsonObj);
                jsonArrCount++;
            }
        }
        //        System.out.println(jsonArray.length());
        xssfWorkbook.close();
        return new JSONObject().put("data", jsonArray);
    }

    //    @SuppressWarnings("unchecked")
    //    // 创建excel文件函数
    //    // src为待保存的文件路径,json为待保存的json数据
    //    public static JSONObject createExcel(String src, JSONObject json) {
    //        JSONObject result = new JSONObject(); // 用来反馈函数调用结果
    //
    //
    //        try {
    //            // 新建文件
    //            File file = new File(src);
    //            file.createNewFile();
    //
    //
    //            OutputStream outputStream = new FileOutputStream(file);// 创建工作薄
    //            WritableWorkbook writableWorkbook = Workbook.createWorkbook(outputStream);
    //            WritableSheet sheet = writableWorkbook.createSheet("First sheet", 0);// 创建新的一页
    //
    //            JSONArray jsonArray = json.getJSONArray("data");// 得到data对应的JSONArray
    //            Label label; // 单元格对象
    //            int column = 0; // 列数计数
    //
    //            // 将第一行信息加到页中。如：姓名、年龄、性别
    //            JSONObject first = jsonArray.getJSONObject(0);
    //            Iterator<String> iterator = first.keys(); // 得到第一项的key集合
    //            while (iterator.hasNext()) { // 遍历key集合
    //                String key = (String) iterator.next(); // 得到key
    //                label = new Label(column++, 0, key); // 第一个参数是单元格所在列,第二个参数是单元格所在行,第三个参数是值
    //                sheet.addCell(label); // 将单元格加到页
    //            }
    //
    //            // 遍历jsonArray
    //            for (int i = 0; i < jsonArray.size(); i++) {
    //                JSONObject item = jsonArray.getJSONObject(i); // 得到数组的每项
    //                iterator = item.keys(); // 得到key集合
    //                column = 0;// 从第0列开始放
    //                while (iterator.hasNext()) {
    //                    String key = iterator.next(); // 得到key
    //                    String value = item.getString(key); // 得到key对应的value
    //                    label = new Label(column++, (i + 1), value); // 第一个参数是单元格所在列,第二个参数是单元格所在行,第三个参数是值
    //                    sheet.addCell(label); // 将单元格加到页
    //                }
    //            }
    //            writableWorkbook.write(); // 加入到文件中
    //            writableWorkbook.close(); // 关闭文件，释放资源
    //        } catch (Exception e) {
    //            result.put("result", "failed"); // 将调用该函数的结果返回
    //            result.put("reason", e.getMessage()); // 将调用该函数失败的原因返回
    //            return result;
    //        }
    //
    //
    //        result.put("result", "successed");
    //        return result;
    //    }

    /**
     * topName is returned.
     * <br>
     * @return  topName
     */
    public String getTopName() {
        return topName;
    }

    /**
     * topName is set up.
     * <br>
     * @param topName String
     */
    public void setTopName(String topName) {
        this.topName = topName;
    }

}