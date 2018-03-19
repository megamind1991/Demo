/**
 * 
 */
package com.me.ga.entity;

/**
 * @author GLAD.Author	
 * @version $Id$
 * @since JDK5.0
 */
public class CaseProcessInfo {

    private String infoId;

    private String caseSeqno;

    private String startDate;

    private String downPe;

    private String answerDeliveryDate;

    private String downEstimates;

    private String caseType;

    private String importance;

    private String downReview;

    private String downStatus;

    private String downActualEstimates;

    private String btype;

    private String downActualPe;

    /**
     * infoId is returned.
     * <br>
     * @return  infoId
     */
    public String getInfoId() {
        return infoId;
    }

    /**
     * infoId is set up.
     * <br>
     * @param infoId String
     */
    public void setInfoId(String infoId) {
        if (infoId == null || infoId == "") {
            infoId = " ";
        }
        this.infoId = infoId;
    }

    /**
     * caseSeqno is returned.
     * <br>
     * @return  caseSeqno
     */
    public String getCaseSeqno() {
        return caseSeqno;
    }

    /**
     * caseSeqno is set up.
     * <br>
     * @param caseSeqno String
     */
    public void setCaseSeqno(String caseSeqno) {
        if (caseSeqno == null || caseSeqno == "") {
            caseSeqno = " ";
        }
        this.caseSeqno = caseSeqno;
    }

    /**
     * startDate is returned.
     * <br>
     * @return  startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * startDate is set up.
     * <br>
     * @param startDate String
     */
    public void setStartDate(String startDate) {
        if (startDate == null || startDate == "") {
            startDate = " ";
        }
        this.startDate = startDate;
    }

    /**
     * downPe is returned.
     * <br>
     * @return  downPe
     */
    public String getDownPe() {
        return downPe;
    }

    /**
     * downPe is set up.
     * <br>
     * @param downPe String
     */
    public void setDownPe(String downPe) {
        if (downPe == null || downPe == "") {
            downPe = " ";
        }
        this.downPe = downPe;
    }

    /**
     * answerDeliveryDate is returned.
     * <br>
     * @return  answerDeliveryDate
     */
    public String getAnswerDeliveryDate() {
        return answerDeliveryDate;
    }

    /**
     * answerDeliveryDate is set up.
     * <br>
     * @param answerDeliveryDate String
     */
    public void setAnswerDeliveryDate(String answerDeliveryDate) {
        if (answerDeliveryDate == null || answerDeliveryDate == "") {
            answerDeliveryDate = " ";
        }
        this.answerDeliveryDate = answerDeliveryDate;
    }

    /**
     * downEstimates is returned.
     * <br>
     * @return  downEstimates
     */
    public String getDownEstimates() {
        return downEstimates;
    }

    /**
     * downEstimates is set up.
     * <br>
     * @param downEstimates String
     */
    public void setDownEstimates(String downEstimates) {
        if (downEstimates == null || downEstimates == "") {
            downEstimates = " ";
        }
        this.downEstimates = downEstimates;
    }

    /**
     * caseType is returned.
     * <br>
     * @return  caseType
     */
    public String getCaseType() {
        return caseType;
    }

    /**
     * caseType is set up.
     * <br>
     * @param caseType String
     */
    public void setCaseType(String caseType) {
        if (caseType == null || caseType == "") {
            caseType = " ";
        }
        this.caseType = caseType;
    }

    /**
     * importance is returned.
     * <br>
     * @return  importance
     */
    public String getImportance() {
        return importance;
    }

    /**
     * importance is set up.
     * <br>
     * @param importance String
     */
    public void setImportance(String importance) {
        if (importance == null || importance == "") {
            importance = " ";
        }
        this.importance = importance;
    }

    /**
     * downReview is returned.
     * <br>
     * @return  downReview
     */
    public String getDownReview() {
        return downReview;
    }

    /**
     * downReview is set up.
     * <br>
     * @param downReview String
     */
    public void setDownReview(String downReview) {
        if (downReview == null || downReview == "") {
            downReview = " ";
        }
        this.downReview = downReview;
    }

    /**
     * downStatus is returned.
     * <br>
     * @return  downStatus
     */
    public String getDownStatus() {
        return downStatus;
    }

    /**
     * downStatus is set up.
     * <br>
     * @param downStatus String
     */
    public void setDownStatus(String downStatus) {
        if (downStatus == null || downStatus == "") {
            downStatus = " ";
        }
        this.downStatus = downStatus;
    }

    /**
     * downActualEstimates is returned.
     * <br>
     * @return  downActualEstimates
     */
    public String getDownActualEstimates() {
        return downActualEstimates;
    }

    /**
     * downActualEstimates is set up.
     * <br>
     * @param downActualEstimates String
     */
    public void setDownActualEstimates(String downActualEstimates) {
        if (downActualEstimates == null || downActualEstimates == "") {
            downActualEstimates = " ";
        }
        this.downActualEstimates = downActualEstimates;
    }

    /**
     * btype is returned.
     * <br>
     * @return  btype
     */
    public String getBtype() {
        return btype;
    }

    /**
     * btype is set up.
     * <br>
     * @param btype String
     */
    public void setBtype(String btype) {
        if (btype == null || btype == "") {
            btype = " ";
        }
        this.btype = btype;
    }

    /**
     * downActualPe is returned.
     * <br>
     * @return  downActualPe
     */
    public String getDownActualPe() {
        return downActualPe;
    }

    /**
     * downActualPe is set up.
     * <br>
     * @param downActualPe String
     */
    public void setDownActualPe(String downActualPe) {
        if (downActualPe == null || downActualPe == "") {
            downActualPe = " ";
        }
        this.downActualPe = downActualPe;
    }

    /**
     * {@inheritDoc}
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CaseProcessInfo [infoId=" + infoId + ", caseSeqno=" + caseSeqno
            + ", startDate=" + startDate + ", downPe=" + downPe
            + ", answerDeliveryDate=" + answerDeliveryDate + ", downEstimates="
            + downEstimates + ", caseType=" + caseType + ", importance="
            + importance + ", downReview=" + downReview + ", downStatus="
            + downStatus + ", downActualEstimates=" + downActualEstimates
            + ", btype=" + btype + ", downActualPe=" + downActualPe + "]";
    }

}
