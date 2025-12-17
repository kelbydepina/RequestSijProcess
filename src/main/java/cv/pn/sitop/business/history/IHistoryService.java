package cv.pn.sitop.business.history;

import cv.pn.sitop.business.report.Report;
import cv.pn.sitop.utilities.APIResponse;

public interface IHistoryService {
    APIResponse saveHistory(HistoryDto dto);

    APIResponse getHistoryByReport(String reportId);

    void register(Report report, String state, String action, String performedBy, String reportId);

    void autoHistoryOccurrence(Report report, String turno, String action, String observations, String performedBy);
}
