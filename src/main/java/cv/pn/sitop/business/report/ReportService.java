package cv.pn.sitop.business.report;






import cv.pn.sitop.business.history.IHistoryService;
import cv.pn.sitop.utilities.APIResponse;
import cv.pn.sitop.utilities.MessageState;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ReportService implements IReportService {

    private final ReportRepository reportRepository;

    private final IHistoryService iHistoryService;

    public ReportService(ReportRepository reportRepository, IHistoryService iHistoryService) {
        this.reportRepository = reportRepository;

        this.iHistoryService = iHistoryService;
    }


    @Override
    public APIResponse saveReport(ReportDto dto) {

        try {

            boolean existReport = reportRepository.existsByStatusCodeAndStatusDescription("AB", "ABERTA");

            if (existReport) {
                return new APIResponse.buildAPIResponse()
                        .setStatus(false)
                        .setStatusText("Já existe um relatório aberto")
                        .setDetails(Collections.singletonList("Feche o relatório atual antes de criar um novo."))
                        .builder();
            }

            Report report = new Report();
            BeanUtils.copyProperties(dto, report);
            //relatorio.getOcorrencias().add(new Ocorrencias());
            // 1ª fase
            report.setPhasesCode("RO");
            report.setPhasesDescription("recolha");
            report.setStatusCode("FE");
            report.setStatusDescription("FECHADA");
            report.setUserCreate("ADMIN");
            report.setActive(true);

            reportRepository.save(report);

            // Registrar histórico automaticamente
            iHistoryService.register(
                    report,
                    "AB",                       // Código da ação
                    "RELATÓRIO FOI ABERTO",      // Descrição da ação
                    "Relatório criado pela primeira vez",          // Observação
                    "ADMIN"                      // Quem fez a ação
            );

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setDetails(Collections.singletonList(report))
                    .setStatusText(MessageState.SUCESSO)
                    .builder();

        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText(MessageState.ERRO)
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }

    @Override
    public APIResponse GetAllReport() {

        try {

            List<ReportDto> reportDtos = reportRepository.findAll().stream().map(report -> {
                ReportDto dto = new ReportDto();
                BeanUtils.copyProperties(report, dto);


                return dto;

            }).collect(Collectors.toList());

            if (reportDtos.isEmpty()) {
                return new APIResponse.buildAPIResponse()
                        .setStatus(false)
                        .setStatusText("Nenhum relatorio encontrada")
                        .setDetails(Collections.emptyList())
                        .builder();
            }

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Lista de relatorio com sucesso")
                    .setDetails(Collections.singletonList(reportDtos))
                    .builder();


        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao listar relatorios")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }

    @Override
    public APIResponse getReportId(String id) {

        try {

            Optional<Report> report = reportRepository.findById(id);

            if (report.isEmpty()) {

                return new APIResponse.buildAPIResponse()
                        .setStatus(false)
                        .setStatusText("Relatorio Não encontrado com ID" )
                        .setDetails(Collections.singletonList(report))
                        .builder();

            }



            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Sucesso")
                    .setDetails(Collections.singletonList(report.get()))
                    .builder();


        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao buscar relatório")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }

    @Override
    public APIResponse updateReport(String id, ReportDto dto) {

        try {

            Optional<Report> optionalReport = reportRepository.findById(id);

            if (optionalReport.isEmpty()) {

                return new APIResponse.buildAPIResponse()
                        .setStatus(false)
                        .setStatusText("Relatório não encontrado com ID: " + id)
                        .setDetails(Collections.singletonList(id))
                        .builder();
            }

            Report report = optionalReport.get();
            BeanUtils.copyProperties(dto, report,"id","phaseCode", "phaseDescription", "statusCode", "statusDescription");
            report.setUserCreate("ADMIN");

            reportRepository.save(report);

            //Registrar histórico de atualização
            /*historyService.register(
                    report.getStatusDescription(),
                    "RELATÓRIO FOI ATUALIZADO",
                    "ADMIN",
                    report.getId()
            );*/

           iHistoryService.register(
                    report,
                    "AT",                           // Código da ação
                    "RELATÓRIO FOI ATUALIZADO",     // Descrição
                    "Relatório foi modificado",      // Observação
                    "ADMIN"
            );

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Relatório atualizado com sucesso")
                    .setDetails(Collections.singletonList(report))
                    .builder();


        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao atualizar relatório")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }

    @Override
    public APIResponse delectReport(String id) {

        try {

            Optional<Report> reportOptional = reportRepository.findById(id);

            if (reportOptional.isEmpty()) {

                return new APIResponse.buildAPIResponse()
                        .setStatus(false)
                        .setStatusText("Relatório não encontrado com ID: " + id)
                        .setDetails(Collections.singletonList(id))
                        .builder();
            }

            Report report = reportOptional.get();

           // reportRepository.delete(reportOptional.get());

            //Registrar histórico de fechamento
           /* historyService.register(
                    "FECHADO",
                    "RELATÓRIO FOI REMOVIDO",
                    "ADMIN",
                    id
            );*/

            // Histórico automático ANTES de deletar
           iHistoryService.register(
                    report,
                    "RM",
                    "RELATÓRIO FOI REMOVIDO",
                    "O relatório foi eliminado",
                    "ADMIN"
            );

            reportRepository.delete(reportOptional.get());

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Relatório removido com sucesso")
                    .setDetails(Collections.singletonList("ID removido: " + id))
                    .builder();


        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao remover relatório")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }


   /* @Override
    // Transição de fase - controla avanço de status/fase
    public APIResponse updatePhase(String id) {

        try {
            Optional<Report> optionalReport = reportRepository.findById(id);

            if (optionalReport.isEmpty()) {
                return new APIResponse.buildAPIResponse()
                        .setStatus(false)
                        .setStatusText("Relatório não encontrado com ID: " + id)
                        .builder();
            }

            Report report = optionalReport.get();

            // Transições automáticas
            switch (report.getPhasesCode()) {
                case "RO":
                   report.setPhasesCode("VD");
                   report.setPhasesDescription("Por Validar");
                   report.setStatusCode("FE");
                   report.setStatusDescription("Fechado");
                    break;

                case "VD":
                    report.setPhasesCode("PB");
                    report.setPhasesDescription("Publicado");
                    report.setStatusCode("FE");
                    report.setStatusDescription("Fechado");
                    break;

                case "PB":
                    return new APIResponse.buildAPIResponse()
                            .setStatus(false)
                            .setStatusText("O relatório já está publicado, não pode avançar de fase.")
                            .builder();

                default:
                    return new APIResponse.buildAPIResponse()
                            .setStatus(false)
                            .setStatusText("Fase desconhecida: " + report.getPhasesCode())
                            .builder();
            }

            reportRepository.save(report);

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Relatório avançou para a fase: " + report.getPhasesDescription())
                    .setDetails(Collections.singletonList(report))
                    .builder();

        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao atualizar fase do relatório")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }*/
}
