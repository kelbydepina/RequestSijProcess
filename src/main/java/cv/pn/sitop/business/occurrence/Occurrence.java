package cv.pn.sitop.business.occurrence;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import cv.pn.sitop.business.occurrenceData.OccurrenceData;
import cv.pn.sitop.business.report.Report;
import cv.pn.sitop.commons.CommonsAttributes;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria_ocorrencias")
public class Occurrence extends CommonsAttributes {

    @Column(name = "graduate_service ")
    private String graduateService;

    @Column(name = "shift")
    private String shift;

    @Column(name = "turn")
    private String turn;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;


    // Relação com Ocorrencia (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Report report;

    // Relação com OcorrenciaDados (1:N)
    @OneToMany(mappedBy = "occurrence", cascade = CascadeType.ALL)//, fetch = FetchType.LAZY)
    //@com.fasterxml.jackson.annotation.JsonManagedReference
    @JsonManagedReference
    private List<OccurrenceData> occurrenceData = new ArrayList<>();


    public Occurrence () {}

    //Métudo seguro para adicionar dados (evita quebrar a lista)
   /* public void addOccurrenceData(OccurrenceData data) {
        occurrenceData.add(data);
        data.setOccurrence(this);
    }*/

    //Métudo para substituir os dados corretamente
    /*public void setOccurrenceData(List<OccurrenceData> newDataList) {
        this.occurrenceData.clear(); // limpa, sem perder a referência
        if (newDataList != null) {
            newDataList.forEach(this::addOccurrenceData);
        }
    }*/

    // Adiciona dados de ocorrência
   /* public void addOcorreciaDados(OcorrenciaDados dados) {
        ocorrenciaDados.add(dados);
        dados.setOcorrencia(this);
    }*/

    public String getGraduateService() {
        return graduateService;
    }

    public void setGraduateService(String graduateService) {
        this.graduateService = graduateService;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

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

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public List<OccurrenceData> getOccurrenceData() {
        return occurrenceData;
    }

    public void setOccurrenceData(List<OccurrenceData> occurrenceData) {
        this.occurrenceData = occurrenceData;
    }
}
