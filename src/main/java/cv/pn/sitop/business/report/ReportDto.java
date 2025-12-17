package cv.pn.sitop.business.report;

import java.time.LocalDate;


public class ReportDto {


    private LocalDate startDate;


    private LocalDate endDate;


    private String startTime;


    private String endTime;


    private String referencPeriod;


    private String commandCode;


    private String commnanDescription;


    private String organicDescription;


    private String organicCode;


    private String statusCode;


    private String statusDescription;

    private String phasesDescription;

    private String phasesCode;



    public ReportDto() {}


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getReferencPeriod() {
        return referencPeriod;
    }

    public void setReferencPeriod(String referencPeriod) {
        this.referencPeriod = referencPeriod;
    }

    public String getCommandCode() {
        return commandCode;
    }

    public void setCommandCode(String commandCode) {
        this.commandCode = commandCode;
    }

    public String getCommnanDescription() {
        return commnanDescription;
    }

    public void setCommnanDescription(String commnanDescription) {
        this.commnanDescription = commnanDescription;
    }

    public String getOrganicDescription() {
        return organicDescription;
    }

    public void setOrganicDescription(String organicDescription) {
        this.organicDescription = organicDescription;
    }

    public String getOrganicCode() {
        return organicCode;
    }

    public void setOrganicCode(String organicCode) {
        this.organicCode = organicCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getPhasesDescription() {
        return phasesDescription;
    }

    public void setPhasesDescription(String phasesDescription) {
        this.phasesDescription = phasesDescription;
    }

    public String getPhasesCode() {
        return phasesCode;
    }

    public void setPhasesCode(String phasesCode) {
        this.phasesCode = phasesCode;
    }
}
