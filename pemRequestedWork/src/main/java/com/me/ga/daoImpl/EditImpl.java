/**
 * 
 */
package com.me.ga.daoImpl;

import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.me.ga.entity.Case;
import com.me.ga.entity.CaseForDis;
import com.me.ga.entity.CaseProcessInfo;
import com.me.ga.mapper.CriteriaMapper;
import com.me.ga.util.LoggersUtil;

/**
 * @author GLAD.Author	
 * @version $Id$
 * @since JDK5.0
 */
@Repository(value = "edit")
public class EditImpl {

    @Autowired
    @Qualifier(value = "criteriaMapper")
    private CriteriaMapper criteriaMapper;

    public void saveAll(JSONArray array) {

        criteriaMapper.delete$case();
        criteriaMapper.delete$caseProcessInfo();

        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);

            Case vcase = new Case();
            vcase.setCaseSeqno(jsonObject.getString("SEQNO"));
            vcase.setDealType(jsonObject.getString("依頼作業"));
            vcase.setDesiredDeliveryDate(jsonObject.getString("希望納期"));
            vcase.setSpecificationIssueDate(jsonObject.getString("仕様発行日"));
            vcase.setFdVer(jsonObject.get("FDver").toString());
            vcase.setProjectId(jsonObject.getString("プロダクトID"));
            vcase.setProjectType(jsonObject.getString("プロダクト種別"));
            vcase.setQuNum(jsonObject.getString("問題管理NO"));
            vcase.setSeName(jsonObject.getString("担当SE"));
            vcase.setTimeCompliance(jsonObject.getString("納期遵守"));
            //            vcase.setUpEstimates(jsonObject.getNumber("上流見積").toString());
            vcase.setUpEstimates(jsonObject.get("上流見積").toString());
            criteriaMapper.insert$case(vcase);

            CaseProcessInfo caseProcessInfo = new CaseProcessInfo();
            caseProcessInfo.setAnswerDeliveryDate(jsonObject.get("回答納期")
                .toString());
            caseProcessInfo.setCaseSeqno(jsonObject.getString("SEQNO"));
            caseProcessInfo.setCaseType(jsonObject.get("批案件分类").toString());
            caseProcessInfo.setDownActualEstimates("");
            caseProcessInfo.setDownEstimates("");
            caseProcessInfo.setDownPe(jsonObject.getString("担当PE"));
            caseProcessInfo.setDownReview("");
            caseProcessInfo.setDownStatus("");
            caseProcessInfo.setImportance(jsonObject.getString("优先顺"));
            caseProcessInfo.setInfoId(UUID.randomUUID().toString());
            caseProcessInfo.setStartDate(jsonObject.getString("着手日"));
            caseProcessInfo.setBtype("");
            caseProcessInfo.setDownActualPe("");
            criteriaMapper.insert$caseProcessInfo(caseProcessInfo);
        }

        System.out.println("finish");
    }

    public void update(CaseProcessInfo caseProcessInfo) {

        CaseProcessInfo caseProcessInfo2 =
            criteriaMapper.select$caseProcessInfoByKey(caseProcessInfo
                .getCaseSeqno());

        if (caseProcessInfo.getStartDate() != null) {
            caseProcessInfo2
                .setStartDate(caseProcessInfo.getStartDate().trim());
        }

        if (caseProcessInfo.getAnswerDeliveryDate() != null) {
            caseProcessInfo2.setAnswerDeliveryDate(caseProcessInfo
                .getAnswerDeliveryDate().trim());
        }

        if (caseProcessInfo.getDownPe() != null) {
            caseProcessInfo2.setDownPe(caseProcessInfo.getDownPe().trim());
        }

        if (caseProcessInfo.getDownEstimates() != null) {
            caseProcessInfo2.setDownEstimates(caseProcessInfo
                .getDownEstimates().trim());
        }

        if (caseProcessInfo.getImportance() != null) {
            caseProcessInfo2.setImportance(caseProcessInfo.getImportance()
                .trim());
        }

        if (caseProcessInfo.getBtype() != null) {
            caseProcessInfo2.setBtype(caseProcessInfo.getBtype().trim());
        }

        if (caseProcessInfo.getDownReview() != null) {
            caseProcessInfo2.setDownReview(caseProcessInfo.getDownReview()
                .trim());
        }

        if (caseProcessInfo.getDownStatus() != null) {
            caseProcessInfo2.setDownStatus(caseProcessInfo.getDownStatus()
                .trim());
        }

        if (caseProcessInfo.getDownActualEstimates() != null) {
            caseProcessInfo2.setDownActualEstimates(caseProcessInfo
                .getDownActualEstimates().trim());
        }

        if (caseProcessInfo.getCaseType() != null) {
            caseProcessInfo2.setCaseType(caseProcessInfo.getCaseType().trim());
        }

        if (caseProcessInfo.getDownActualPe() != null) {
            caseProcessInfo2.setDownActualPe(caseProcessInfo.getDownActualPe()
                .trim());
        }

        //        LoggersUtil.info(this.getClass(), caseProcessInfo2.toString());
        criteriaMapper.delete$caseProcessInfoByKey(caseProcessInfo
            .getCaseSeqno());
        criteriaMapper.insert$caseProcessInfo(caseProcessInfo2);
    }

    public JSONArray selectAll() {
        List<CaseForDis> caseForDisList = criteriaMapper.selectall$case();
        JSONArray jsonArray = new JSONArray(caseForDisList);
        return jsonArray;
    }

    public JSONArray search(CaseForDis caseForDis) {
        List<CaseForDis> caseForDisList =
            criteriaMapper.select$specificCase(caseForDis);
        JSONArray jsonArray = new JSONArray(caseForDisList);
        return jsonArray;
    }

    public void saveCase(Case vcase) {

        vcase.setCaseSeqno(vcase.getCaseSeqno().trim());

        vcase.setDealType(vcase.getDealType().trim());

        vcase.setDesiredDeliveryDate(vcase.getDesiredDeliveryDate().trim());

        vcase.setFdVer(vcase.getFdVer().trim());

        vcase.setProjectId(vcase.getProjectId().trim());

        vcase.setProjectType(vcase.getProjectType().trim());

        vcase.setQuNum(vcase.getQuNum().trim());

        vcase.setSeName(vcase.getSeName().trim());

        vcase.setSpecificationIssueDate(vcase.getSpecificationIssueDate()
            .trim());

        vcase.setTimeCompliance(vcase.getTimeCompliance().trim());

        vcase.setUpEstimates(vcase.getUpEstimates().trim());

        criteriaMapper.delete$caseByKey(vcase.getCaseSeqno());
        LoggersUtil.info(this.getClass(), vcase.toString());
        criteriaMapper.insert$case(vcase);
    }

    /**
     * criteriaMapper is returned.
     * <br>
     * @return  criteriaMapper
     */
    public CriteriaMapper getCriteriaMapper() {
        return criteriaMapper;
    }

    /**
     * criteriaMapper is set up.
     * <br>
     * @param criteriaMapper CriteriaMapper
     */
    public void setCriteriaMapper(CriteriaMapper criteriaMapper) {
        this.criteriaMapper = criteriaMapper;
    }

}
