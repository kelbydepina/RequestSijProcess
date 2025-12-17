package cv.pn.sitop.business.fullReportDto;

import cv.pn.sitop.utilities.APIResponse;

public interface IFullReportService {
    APIResponse saveFullReport(FullReportRequestDto dto);

    APIResponse getFullReportById(String reportId);


}
