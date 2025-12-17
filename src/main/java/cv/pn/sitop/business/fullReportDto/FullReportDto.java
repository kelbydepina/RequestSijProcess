package cv.pn.sitop.business.fullReportDto;

import cv.pn.sitop.business.occurrence.OccurrenceDto;

public class FullReportDto {

    private String id;
    private String commandCode;
    private String commnanDescription;
    private String organicDescription;
    private String organicCode;
    private OccurrenceDto occurrence;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public OccurrenceDto getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(OccurrenceDto occurrence) {
        this.occurrence = occurrence;
    }
}
