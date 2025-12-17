package cv.pn.sitop.business.report;

import cv.pn.sitop.business.fullReportDto.FullReportRequestDto;
import cv.pn.sitop.utilities.APIResponse;

public interface IReportService {


    APIResponse saveReport(ReportDto dto);

    APIResponse GetAllReport();

    APIResponse getReportId(String id);

    APIResponse updateReport(String id, ReportDto dto);

    APIResponse delectReport(String id);




}
