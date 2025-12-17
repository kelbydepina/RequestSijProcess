package cv.pn.sitop.business.report;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import cv.pn.sitop.business.history.History;
import cv.pn.sitop.business.occurrence.Occurrence;
import cv.pn.sitop.commons.CommonsAttributes;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "sitop_relatorio")
public class Report extends CommonsAttributes {

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "reference_period")
    private String referencPeriod;

    @Column(name = "command_code ")
    private String commandCode;

    @Column(name = "command_description ")
    private String commnanDescription;

    @Column(name = "organic_description ")
    private String organicDescription;

    @Column(name = "organic_code ")
    private String organicCode;

    @Column(name = "status_code ")
    private String statusCode;

    @Column(name = "status_description ")
    private String statusDescription;

    @Column(name = "phases_description ")
    private String phasesDescription;

    @Column(name = "phases_code ")
    private String phasesCode;

    //private String ocorrenciaId;


    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonManagedReference("report-occurrences")
    @JsonIgnore
    private List<Occurrence> occurrences = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<History> histories;



   /* public void addOcorrencia(Ocorrencias ocorrencia) {
        ocorrencias.add(ocorrencia);
        ocorrencia.setRelatorio(this);
    }*/

    public Report() {}


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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
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

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescripton(String statusDescripton) {
        this.statusDescription = statusDescripton;
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

    public List<Occurrence> getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(List<Occurrence> occurrences) {
        this.occurrences = occurrences;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }
}
