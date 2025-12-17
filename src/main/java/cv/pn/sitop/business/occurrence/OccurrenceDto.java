package cv.pn.sitop.business.occurrence;





import cv.pn.sitop.business.occurrenceData.OccurrenceDataRequestDto;

import java.time.LocalDate;
import java.util.List;


public class OccurrenceDto {


    private String id;

    private String graduateService;

    private String shift;

    private String turn;

    private LocalDate startDate;

    private LocalDate endDate;

    private String reportId;

    private List<OccurrenceDataRequestDto> occurrenceData;

   // private String occurrenceDataId;




    public OccurrenceDto() {}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public List<OccurrenceDataRequestDto> getOccurrenceData() {
        return occurrenceData;
    }

    public void setOccurrenceData(List<OccurrenceDataRequestDto> occurrenceData) {
        this.occurrenceData = occurrenceData;
    }
}
