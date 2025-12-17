package cv.pn.sitop.business.history;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cv.pn.sitop.business.report.Report;
import cv.pn.sitop.commons.CommonsAttributes;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "historico_sitop")
public class History extends CommonsAttributes {

    @Column(name = "dm_report_phase_description") // TODOS NOT NULL
    private String reportPhaseDescription;

    @Column(name = "dm_report_phase_code")
    private String reportPhaseCode;

    @Column(name = "dm_status_description")
    private String reportStatusDescription;

    @Column(name = "dm_status_code")
    private String reportStatusCode;
    @Column(name = "decision")
    private String decision;
    @Column(name = "observations")
    private String observations;

    @Column(name = "dm_action_type_description")
    private String actionTypeDescription;

    @Column(name = "dm_action_type_code")
    private String actionTypeCode;

    @Column(name = "action_performed_by", nullable = false)
    private String actionPerformedBy;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

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

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }


    public String getActionPerformedBy() {
        return actionPerformedBy;
    }

    public void setActionPerformedBy(String actionPerformedBy) {
        this.actionPerformedBy = actionPerformedBy;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
