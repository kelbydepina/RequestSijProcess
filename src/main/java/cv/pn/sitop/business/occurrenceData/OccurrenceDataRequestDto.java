package cv.pn.sitop.business.occurrenceData;

import java.util.List;

public class OccurrenceDataRequestDto {

    private String typeDescription;
    private String typeCode;
    private String groupDescription;
    private String groupCode;
    private String response;
    private String occurrenceId; // Relacionamento: pertence a um Occurrence

    public OccurrenceDataRequestDto() {

    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setOccurrenceData(List<OccurrenceDataRequestDto> dataDtos) {
    }

    public String getOccurrenceId() {
        return occurrenceId;
    }

    public void setOccurrenceId(String occurrenceId) {
        this.occurrenceId = occurrenceId;
    }
}
