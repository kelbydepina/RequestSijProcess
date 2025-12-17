package cv.pn.sitop.business.occurrence;

import cv.pn.sitop.utilities.APIResponse;

public  interface IOccurrenceServices {
    //APIResponse saveOcorrencias(Ocorrencias ocorrencias, String relatorioId);
    //APIResponse getAllOcorrencias();

    //APIResponse getOcorreciasId(String relatorioId);

    //APIResponse getAllOcorrencias();

   // APIResponse getAllOccurrence();

   // APIResponse getOccurrence(String reportId);

   // APIResponse saveOccurrence(OccurrenceDto dto);

    APIResponse createOccurrence(String reportId, OccurrenceDto occurrenceDto);

    APIResponse getOccurrencesByReport(String reportId);

    // APIResponse getOccurrencesByReportId(String reportId);


    //List<Map<String, Object>> getDescricaoERespostaPorRelatorio(String relatorioId);
}
