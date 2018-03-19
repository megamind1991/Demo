/**
 * 
 */
package com.me.ga.entity;


/**
 * @author GLAD.Author	
 * @version $Id$
 * @since JDK5.0
 */
public class Case {

    private String caseSeqno;

    private String quNum;

    private String projectId;

    private String fdVer;

    private String dealType;

    private String projectType;

    private String specificationIssueDate;

    private String desiredDeliveryDate;

    private String timeCompliance;

    private String upEstimates;

    private String seName;

    
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
     * quNum is returned.
     * <br>
     * @return  quNum
     */
    public String getQuNum() {
        return quNum;
    }

    
    /**
     * quNum is set up.
     * <br>
     * @param quNum String
     */
    public void setQuNum(String quNum) {
        if (quNum == null || quNum == "") {
            quNum = " ";
        }
        this.quNum = quNum;
    }

    
    /**
     * projectId is returned.
     * <br>
     * @return  projectId
     */
    public String getProjectId() {
        return projectId;
    }

    
    /**
     * projectId is set up.
     * <br>
     * @param projectId String
     */
    public void setProjectId(String projectId) {
        if (projectId == null || projectId == "") {
            projectId = " ";
        }
        this.projectId = projectId;
    }

    
    /**
     * fdVer is returned.
     * <br>
     * @return  fdVer
     */
    public String getFdVer() {
        return fdVer;
    }

    
    /**
     * fdVer is set up.
     * <br>
     * @param fdVer String
     */
    public void setFdVer(String fdVer) {
        if (fdVer == null || fdVer == "") {
            fdVer = " ";
        }
        this.fdVer = fdVer;
    }

    
    /**
     * dealType is returned.
     * <br>
     * @return  dealType
     */
    public String getDealType() {
        return dealType;
    }

    
    /**
     * dealType is set up.
     * <br>
     * @param dealType String
     */
    public void setDealType(String dealType) {
        if (dealType == null || dealType == "") {
            dealType = " ";
        }
        this.dealType = dealType;
    }

    
    /**
     * projectType is returned.
     * <br>
     * @return  projectType
     */
    public String getProjectType() {
        return projectType;
    }

    
    /**
     * projectType is set up.
     * <br>
     * @param projectType String
     */
    public void setProjectType(String projectType) {
        if (projectType == null || projectType == "") {
            projectType = " ";
        }
        this.projectType = projectType;
    }

    
    /**
     * specificationIssueDate is returned.
     * <br>
     * @return  specificationIssueDate
     */
    public String getSpecificationIssueDate() {
        return specificationIssueDate;
    }

    
    /**
     * specificationIssueDate is set up.
     * <br>
     * @param specificationIssueDate String
     */
    public void setSpecificationIssueDate(String specificationIssueDate) {
        if (specificationIssueDate == null || specificationIssueDate == "") {
            specificationIssueDate = " ";
        }
        this.specificationIssueDate = specificationIssueDate;
    }

    
    /**
     * desiredDeliveryDate is returned.
     * <br>
     * @return  desiredDeliveryDate
     */
    public String getDesiredDeliveryDate() {
        return desiredDeliveryDate;
    }

    
    /**
     * desiredDeliveryDate is set up.
     * <br>
     * @param desiredDeliveryDate String
     */
    public void setDesiredDeliveryDate(String desiredDeliveryDate) {
        if (desiredDeliveryDate == null || desiredDeliveryDate == "") {
            desiredDeliveryDate = " ";
        }
        this.desiredDeliveryDate = desiredDeliveryDate;
    }

    
    /**
     * timeCompliance is returned.
     * <br>
     * @return  timeCompliance
     */
    public String getTimeCompliance() {
        return timeCompliance;
    }

    
    /**
     * timeCompliance is set up.
     * <br>
     * @param timeCompliance String
     */
    public void setTimeCompliance(String timeCompliance) {
        if (timeCompliance == null || timeCompliance == "") {
            timeCompliance = " ";
        }
        this.timeCompliance = timeCompliance;
    }

    
    /**
     * upEstimates is returned.
     * <br>
     * @return  upEstimates
     */
    public String getUpEstimates() {
        return upEstimates;
    }

    
    /**
     * upEstimates is set up.
     * <br>
     * @param upEstimates String
     */
    public void setUpEstimates(String upEstimates) {
        if (upEstimates == null || upEstimates == "") {
            upEstimates = " ";
        }
        this.upEstimates = upEstimates;
    }

    
    /**
     * seName is returned.
     * <br>
     * @return  seName
     */
    public String getSeName() {
        return seName;
    }

    
    /**
     * seName is set up.
     * <br>
     * @param seName String
     */
    public void setSeName(String seName) {
        if (seName == null || seName == "") {
            seName = " ";
        }
        this.seName = seName;
    }


    /**
     * {@inheritDoc}
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Case [caseSeqno=" + caseSeqno + ", quNum=" + quNum
            + ", projectId=" + projectId + ", fdVer=" + fdVer + ", dealType="
            + dealType + ", projectType=" + projectType
            + ", specificationIssueDate=" + specificationIssueDate
            + ", desiredDeliveryDate=" + desiredDeliveryDate
            + ", timeCompliance=" + timeCompliance + ", upEstimates="
            + upEstimates + ", seName=" + seName + "]";
    }

   
  

}
