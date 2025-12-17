package cv.pn.sitop.business.fullReportDto;


import cv.pn.sitop.business.history.History;
import cv.pn.sitop.business.history.HistoryRepository;
import cv.pn.sitop.business.history.IHistoryService;
import cv.pn.sitop.business.occurrence.IOccurrenceServices;
import cv.pn.sitop.business.occurrence.Occurrence;
import cv.pn.sitop.business.occurrence.OccurrenceDto;
import cv.pn.sitop.business.occurrence.OccurrenceRepository;
import cv.pn.sitop.business.occurrenceData.IOccurrenceDataService;
import cv.pn.sitop.business.occurrenceData.OccurrenceData;
import cv.pn.sitop.business.occurrenceData.OccurrenceDataRequestDto;
import cv.pn.sitop.business.report.Report;
import cv.pn.sitop.business.report.ReportRepository;
import cv.pn.sitop.utilities.APIResponse;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FullReportService implements IFullReportService {


    private final ReportRepository reportRepository;
    private final OccurrenceRepository occurrenceRepository;
    private final HistoryRepository historyRepository;
    private final IOccurrenceServices iOccurrenceServices;
    private final IOccurrenceDataService iOccurrenceDataService;
    private final IHistoryService iHistoryService;

    public FullReportService(ReportRepository reportRepository, OccurrenceRepository occurrenceRepository, HistoryRepository historyRepository, IOccurrenceServices iOccurrenceServices, IOccurrenceDataService iOccurrenceDataService, IHistoryService iHistoryService) {
        this.reportRepository = reportRepository;
        this.occurrenceRepository = occurrenceRepository;
        this.historyRepository = historyRepository;
        this.iOccurrenceServices = iOccurrenceServices;
        this.iOccurrenceDataService = iOccurrenceDataService;
        this.iHistoryService = iHistoryService;
    }


    @Override
    public APIResponse saveFullReport(FullReportRequestDto dto) {

        try {

            // Verificar se existe relatório ABERTO
            boolean existOpen = reportRepository.existsByStatusCodeAndStatusDescription("AB", "ABERTA");

            if (existOpen) {
                return new APIResponse.buildAPIResponse()
                        .setStatus(false)
                        .setStatusText("Já existe um relatório aberto")
                        .setDetails(Collections.singletonList("Feche o relatório atual antes de criar um novo."))
                        .builder();
            }

            List<Map<String, Object>> resultList = new ArrayList<>();

            for (FullReportDto reportDto : dto.getReport()) {


                // 1. Criar o RELATÓRIO

                Report report = new Report();
                report.setCommandCode(reportDto.getCommandCode());
                report.setCommnanDescription(reportDto.getCommnanDescription());
                report.setOrganicDescription(reportDto.getOrganicDescription());
                report.setOrganicCode(reportDto.getOrganicCode());

                report.setPhasesCode("RO");
                report.setPhasesDescription("Recolha");

                report.setStatusCode("FE");
                report.setStatusDescription("FECHADA");

                report.setUserCreate("ADMIN");
                report.setActive(true);

                reportRepository.save(report);

                // GERAR HISTÓRICO "RELATÓRIO ABERTO"
                iHistoryService.register(
                        report,
                        "AB",
                        "RELATÓRIO FOI ABERTO",
                        "Relatório criado pela primeira vez",
                        "ADMIN"
                );


                // 2. Criar OCORRÊNCIA (Turno)

                OccurrenceDto occInput = reportDto.getOccurrence();
                Occurrence occurrence = null;

                if (occInput != null) {

                    long count = occurrenceRepository.countByReportId(report.getId());
                    String turnoGerado = (count + 1) + "º Turno";

                    occurrence = new Occurrence();
                    occurrence.setGraduateService(occInput.getGraduateService());
                    occurrence.setShift(occInput.getShift());
                    occurrence.setStartDate(occInput.getStartDate());
                    occurrence.setEndDate(occInput.getEndDate());
                    occurrence.setTurn(turnoGerado);
                    occurrence.setReport(report);
                    occurrence.setUserCreate("ADMIN");

                    occurrenceRepository.save(occurrence);

                    // Histórico automático
                    iHistoryService.autoHistoryOccurrence(
                            report,
                            occurrence.getTurn(),
                            "OCORRÊNCIA CRIADA",
                            "Nova ocorrência registrada no turno " + occurrence.getTurn(),
                            "ADMIN"
                    );

                    // Salvar occurrenceData (usando métudo extraído)
                    //saveOccurrenceDataList(occInput.getOccurrenceData(), occurrence);


                    // 3. Criar occurrenceData

                    if (occInput.getOccurrenceData() != null) {

                        for (OccurrenceDataRequestDto dataDto : occInput.getOccurrenceData()) {

                            OccurrenceData data = new OccurrenceData();
                            data.setDescription(dataDto.getTypeDescription());
                            data.setCode(dataDto.getTypeCode());
                            data.setGroupCode(dataDto.getGroupCode());
                            data.setGroupDescription(dataDto.getGroupDescription());
                            data.setResponse(dataDto.getResponse());
                            data.setOccurrence(occurrence);
                            data.setUserCreate("ADMIN");

                            iOccurrenceDataService.save(data);
                        }
                    }
                }



                // 4. Montar retorno do relatório salvo
                
                Map<String, Object> response = new HashMap<>();
                response.put("reportId", report.getId());
                response.put("commandCode", report.getCommandCode());
                response.put("organicDescription", report.getOrganicDescription());
                response.put("status", report.getStatusDescription());

                if (occurrence != null) {
                    Map<String, Object> occMap = new HashMap<>();
                    occMap.put("id", occurrence.getId());
                    occMap.put("graduateService", occurrence.getGraduateService());
                    occMap.put("turnoDas", occurrence.getShift());
                    occMap.put("turno", occurrence.getTurn());
                    occMap.put("data", occurrence.getStartDate());

                    response.put("occurrence", occMap);
                }

                resultList.add(response);
            }

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Relatório completo salvo com sucesso")
                    .setDetails(Collections.singletonList(resultList))
                    .builder();

        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao salvar relatório")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }

   /* private void saveOccurrenceDataList(List<OccurrenceDataRequestDto> dataDtos, Occurrence occurrence) {

        if (dataDtos == null || dataDtos.isEmpty()) {
            return;
        }

        for (OccurrenceDataRequestDto dto : dataDtos) {
            OccurrenceData data = new OccurrenceData();

            data.setDescription(dto.getTypeDescription());
            data.setCode(dto.getTypeCode());
            data.setGroupCode(dto.getGroupCode());
            data.setGroupDescription(dto.getGroupDescription());
            data.setResponse(dto.getResponse());

            data.setOccurrence(occurrence);
            data.setUserCreate("ADMIN");

            iOccurrenceDataService.save(data);
        }
    }
    }*/

    @Override
    public APIResponse getFullReportById(String reportId) {

        try {
            // 1. Buscar relatório
            Report report = reportRepository.findById(reportId)
                    .orElseThrow(() ->
                            new RuntimeException("Relatório não encontrado com ID: " + reportId));

            // 2. Buscar ocorrências
            List<Occurrence> occurrences = occurrenceRepository.findByReportId(reportId);

            // 3. Buscar histórico
            List<History> histories = historyRepository.findByReportIdOrderByDateTimeDesc(reportId);

            // 4. Montar DTO do Report
            FullReportDto fullReportDto = new FullReportDto();
            fullReportDto.setId(report.getId());
            fullReportDto.setCommandCode(report.getCommandCode());
            fullReportDto.setCommnanDescription(report.getCommnanDescription());
            fullReportDto.setOrganicCode(report.getOrganicCode());
            fullReportDto.setOrganicDescription(report.getOrganicDescription());

            // 5. Occurrence
            if (!occurrences.isEmpty()) {
                Occurrence occ = occurrences.get(0);

                OccurrenceDto occDto = new OccurrenceDto();
                occDto.setId(occ.getId());
                occDto.setGraduateService(occ.getGraduateService());
                occDto.setShift(occ.getShift());
                occDto.setTurn(occ.getTurn());
                occDto.setStartDate(occ.getStartDate());
                occDto.setEndDate(occ.getEndDate());
                occDto.setReportId(report.getId());

                List<OccurrenceDataRequestDto> dataDtos = occ.getOccurrenceData().stream()
                        .map(data -> {
                            OccurrenceDataRequestDto d = new OccurrenceDataRequestDto();
                            d.setTypeDescription(data.getDescription());
                            d.setTypeCode(data.getCode());
                            d.setGroupDescription(data.getGroupDescription());
                            d.setGroupCode(data.getGroupCode());
                            d.setResponse(data.getResponse());
                            d.setOccurrenceId(occ.getId());
                            return d;
                        }).collect(Collectors.toList());

                occDto.setOccurrenceData(dataDtos);
                fullReportDto.setOccurrence(occDto);
            }

            // 6. Histórico
            List<Map<String, Object>> historyList = new ArrayList<>();
            int index = 1;

            for (History h : histories) {
                Map<String, Object> map = new HashMap<>();
                map.put("n", index++);
                map.put("fase", h.getReportPhaseDescription());
                map.put("estado", h.getReportStatusDescription());
                map.put("acao", h.getActionTypeDescription());
                map.put("acaoRealizadaPor", h.getActionPerformedBy());
                map.put("observacao", h.getObservations());
                map.put("dataHora", h.getDateTime());
                historyList.add(map);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("report", fullReportDto);
            response.put("historico", historyList);

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("sucesso")
                    .setDetails(Collections.singletonList(response))
                    .builder();

        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao buscar relatório completo: " + e.getMessage())
                    .builder();
        }
    }
}
