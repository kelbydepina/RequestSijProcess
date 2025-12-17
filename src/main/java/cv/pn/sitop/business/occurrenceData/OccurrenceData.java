package cv.pn.sitop.business.occurrenceData;



import com.fasterxml.jackson.annotation.JsonBackReference;
import cv.pn.sitop.business.occurrence.Occurrence;

import cv.pn.sitop.commons.CommonsAttributes;


import javax.persistence.*;

@Entity
@Table(name = "ocorrencia_dados")
public class OccurrenceData extends CommonsAttributes {


    @Column(name = "description")//obrigatorio
    private String description;
    @Column(name = "name")//obrigatorio
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "sequence")
    private int order;
    @Column(name = "self_code")//ogrigatorio
    private String selfCode;
    @Column(name = "group_code")
    private String groupCode;
    @Column(name = "group_description")
    private String groupDescription;
    @Column(name = "status_code")
    private String statusCode;
    @Column(name = "status_description")
    private String statusDescription;
    @Column(name = "shift")
    private String shift;
    @Column(name = "response")
    private String response;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "occurrence_id")
    @JsonBackReference
    private Occurrence occurrence;


    public OccurrenceData() {}




    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getSelfCode() {
        return selfCode;
    }

    public void setSelfCode(String selfCode) {
        this.selfCode = selfCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
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

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Occurrence getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(Occurrence occurrence) {
        this.occurrence = occurrence;
    }
}
