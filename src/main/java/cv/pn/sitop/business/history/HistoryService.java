package cv.pn.sitop.business.history;


import cv.pn.sitop.business.report.Report;
import cv.pn.sitop.business.report.ReportRepository;
import cv.pn.sitop.utilities.APIResponse;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;

@Service
public class HistoryService implements IHistoryService{

    private final HistoryRepository historyRepository;

    private final ReportRepository reportRepository;

    public HistoryService(HistoryRepository historyRepository, ReportRepository reportRepository) {
        this.historyRepository = historyRepository;
        this.reportRepository = reportRepository;
    }

    @Override
    public APIResponse saveHistory(HistoryDto dto) {

        try {

           Report report = reportRepository.findById(dto.getReportId())
                    .orElseThrow(() -> new RuntimeException("Relatório não encontrado com ID: " + dto.getReportId()));

            History history = new History();

            history.setReport(report);
            history.setDateTime(LocalDateTime.now());
            history.setActionPerformedBy(dto.getActionPerformedBy());


            history.setReportPhaseDescription(dto.getReportPhaseDescription());
            history.setReportPhaseCode(dto.getReportPhaseCode());

            history.setReportStatusDescription(dto.getReportStatusDescription());
            history.setReportStatusCode(dto.getReportStatusCode());

            history.setDecision(dto.getDecision());
            history.setObservations(dto.getObservations());

            history.setActionTypeDescription(dto.getActionTypeDescription());
            history.setActionTypeCode(dto.getActionTypeCode());

            history.setUserCreate("ADMIN");


            historyRepository.save(history);

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Histórico salvo com sucesso")
                    .setDetails(Collections.singletonList("Histórico salvo no relatório " + dto.getReportId()))
                    .builder();

        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao salvar histórico: " + e.getMessage())
                    .builder();
        }
    }

    @Override
    public APIResponse getHistoryByReport(String reportId) {

        try {
            //List<History> historyList = historyRepository.findByReportIdOrderByDateTimeDesc(reportId);

            //List<Map<String, Object>> dtoList = new ArrayList<>();
            //int i = 1;
           /* for (History h : historyList) {
                Map<String, Object> map = new HashMap<>();
                map.put("n", i++);
                map.put("estado", h.getState());
                map.put("dataHora", h.getDateTime());
                map.put("acaoRealizadaPor", h.getPerformedBy());
                map.put("acao", h.getAction());
                dtoList.add(map);
            }*/

            List<History> historyList = historyRepository.findByReportIdOrderByDateTimeDesc(reportId);

            List<Map<String, Object>> dtoList = new ArrayList<>();
            int i = 1;

            for (History h : historyList) {
                Map<String, Object> map = new HashMap<>();
                map.put("n", i++);
                map.put("fase", h.getReportPhaseDescription());
                map.put("estado", h.getReportStatusDescription());
                map.put("acao", h.getActionTypeDescription());
                map.put("acaoRealizadaPor", h.getActionPerformedBy());
                map.put("dataHora", h.getDateTime());
                map.put("observacao", h.getObservations());
                dtoList.add(map);
            }

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("sucesso")
                    .setDetails(Collections.singletonList(dtoList))
                    .builder();

        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao buscar histórico: " + e.getMessage())
                    .builder();
        }
    }


    // Métudo auxiliar para registrar histórico direto do ReportService

    public void register(
            Report report,
            String state,
            String action,
            String observations,
            String performedBy

    ) {
        try {

            History history = new History();

            history.setReport(report);
            history.setDateTime(LocalDateTime.now());
            history.setActionPerformedBy(
                    performedBy != null ? performedBy : "ADMIN"
            );

            // Fase atual do relatório
            history.setReportPhaseCode(report.getPhasesCode());
            history.setReportPhaseDescription(report.getPhasesDescription());

            // Status atual do relatório
            history.setReportStatusCode(report.getStatusCode());
            history.setReportStatusDescription(report.getStatusDescription());

            // Informações da decisão e observações
            history.setDecision("SISTEMA");
            history.setObservations(observations);

            // Ação registrada
            history.setActionTypeCode("AC");     // AC = Ação
            history.setActionTypeDescription(action);

            history.setUserCreate("ADMIN");

            historyRepository.save(history);

        } catch (Exception e) {
            System.err.println("Erro ao registrar histórico padrão: " + e.getMessage());
        }
    }

    @Override
    public void autoHistoryOccurrence(
            Report report,
            String turno,
            String action,
            String observations,
            String performedBy
    ) {
        try {
            History h = new History();

            h.setReport(report);
            h.setDateTime(LocalDateTime.now());

            // Fase atual do relatório
            h.setReportPhaseCode(report.getPhasesCode());
            h.setReportPhaseDescription(report.getPhasesDescription());

            // Estado atual do relatório
            h.setReportStatusCode(report.getStatusCode());
            h.setReportStatusDescription(report.getStatusDescription());

            // Ação
            h.setDecision("SISTEMA");
            h.setObservations(observations);

            h.setActionTypeCode("OC");
            h.setActionTypeDescription(action + " - " + turno);

            h.setActionPerformedBy(performedBy);
            h.setUserCreate(performedBy);

            historyRepository.save(h);

        } catch (Exception e) {
            System.err.println("Erro ao registrar histórico de ocorrência: " + e.getMessage());
        }
    }


}
