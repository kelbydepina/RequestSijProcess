package cv.pn.sitop.business.history;


import cv.pn.sitop.commons.CommonsParametrizationAttributesDto;

public class HistoryDto {

    private String id;
    private String userCreate;
    private CommonsParametrizationAttributesDto processPhase;
    private CommonsParametrizationAttributesDto actionType;
    private CommonsParametrizationAttributesDto processStatus;
    private String decision;
    private String observations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public CommonsParametrizationAttributesDto getProcessPhase() {
        return processPhase;
    }

    public void setProcessPhase(CommonsParametrizationAttributesDto processPhase) {
        this.processPhase = processPhase;
    }

    public CommonsParametrizationAttributesDto getActionType() {
        return actionType;
    }

    public void setActionType(CommonsParametrizationAttributesDto actionType) {
        this.actionType = actionType;
    }

    public CommonsParametrizationAttributesDto getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(CommonsParametrizationAttributesDto processStatus) {
        this.processStatus = processStatus;
    }

    private String reportId;

    private String reportPhaseDescription;

    private String reportPhaseCode;

    private String reportStatusDescription;

    private String reportStatusCode;

    //private String decision;

   // private String observations;

    private String actionTypeDescription;

    private String actionTypeCode;

    private String actionPerformedBy;




    public HistoryDto() {}

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportPhaseDescription() {
        return reportPhaseDescription;
    }

    public void setReportPhaseDescription(String reportPhaseDescription) {
        this.reportPhaseDescription = reportPhaseDescription;
    }

    public String getReportPhaseCode() {
        return reportPhaseCode;
    }

    public void setReportPhaseCode(String reportPhaseCode) {
        this.reportPhaseCode = reportPhaseCode;
    }

    public String getReportStatusDescription() {
        return reportStatusDescription;
    }

    public void setReportStatusDescription(String reportStatusDescription) {
        this.reportStatusDescription = reportStatusDescription;
    }

    public String getReportStatusCode() {
        return reportStatusCode;
    }

    public void setReportStatusCode(String reportStatusCode) {
        this.reportStatusCode = reportStatusCode;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getActionTypeDescription() {
        return actionTypeDescription;
    }

    public void setActionTypeDescription(String actionTypeDescription) {
        this.actionTypeDescription = actionTypeDescription;
    }

    public String getActionTypeCode() {
        return actionTypeCode;
    }

    public void setActionTypeCode(String actionTypeCode) {
        this.actionTypeCode = actionTypeCode;
    }

    public String getActionPerformedBy() {
        return actionPerformedBy;
    }

    public void setActionPerformedBy(String actionPerformedBy) {
        this.actionPerformedBy = actionPerformedBy;
    }


}
