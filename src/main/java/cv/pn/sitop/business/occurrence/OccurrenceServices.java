package cv.pn.sitop.business.occurrence;








import cv.pn.sitop.business.history.IHistoryService;
import cv.pn.sitop.business.report.Report;
import cv.pn.sitop.business.report.ReportRepository;
import cv.pn.sitop.utilities.APIResponse;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.*;



@Service
public class OccurrenceServices implements IOccurrenceServices {

    public final OccurrenceRepository occurrenceRepository;

    public final ReportRepository reportRepository;

    private final IHistoryService iHistoryService;

    public OccurrenceServices(OccurrenceRepository occurrenceRepository, ReportRepository reportRepository, IHistoryService iHistoryService) {
        this.occurrenceRepository = occurrenceRepository;
        this.reportRepository = reportRepository;

        this.iHistoryService = iHistoryService;
    }



   /* @Override
    public APIResponse getAllOccurrence() {

        try {

            List<OccurrenceDataRequestDto> occurrenceDataRequestDto = occurrenceRepository.findAll().stream().map(occurrence -> {

                OccurrenceDataRequestDto dto = new OccurrenceDataRequestDto();
                BeanUtils.copyProperties(occurrence, dto);

               /* if (ocorrencias.getRelatorio() != null) {
                    dto.setRelatorioId(ocorrencias.getRelatorio().getId());
                }

                // Mapeia os occurrenceData
                if (occurrence.getOccurrenceData() != null) {

                    List<OccurrenceDataRequestDto> dataDtos = occurrence.getOccurrenceData().stream().map(data -> {
                        OccurrenceDataRequestDto dataDto = new OccurrenceDataRequestDto();
                        BeanUtils.copyProperties(data, dataDto);

                        return dataDto;

                    }).collect(Collectors.toList());

                    dto.setOccurrenceData(dataDtos);
                }


                return dto;
            }).collect(Collectors.toList());

            if (occurrenceDataRequestDto.isEmpty()) {
                return new APIResponse.buildAPIResponse()
                        .setStatus(false)
                        .setStatusText("Nenhuma ocorrencia encontrada")
                        .setDetails(Collections.emptyList())
                        .builder();
            }

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Lista de ocorrencias com sucesso")
                    .setDetails(Collections.singletonList(occurrenceDataRequestDto))
                    .builder();


        }  catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText(MessageState.ERRO)
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }*/

   /* @Override
    public APIResponse getOccurrence(String reportId) {

        try {

            //boolean relatorioexiste = relatorioRepository.existsById(relatorioId)

            Report report = reportRepository.findById(reportId)
                    .orElseThrow(() -> new RuntimeException("Relatorio nao encontrado com ID: " + reportId));


            // List<Ocorrencias> ocorrenciasList = relatorio.getOcorrencias();

            List<Occurrence> occurrencesList = occurrenceRepository.findByReportId(report.getId());


            if (occurrencesList.isEmpty()) {
                return new APIResponse.buildAPIResponse()
                        .setStatus(true)
                        .setStatusText("Relatório encontrado, mas nenhuma ocorrência registrada")
                        .setDetails(Collections.emptyList())
                        .builder();
            }

            List<OccurrenceDto> occurrenceDtos = occurrencesList.stream().map(occurrence -> {
                OccurrenceDto dto = new OccurrenceDto();
                dto.setId(occurrence.getId());
                dto.setGraduateService(occurrence.getGraduateService());
                dto.setShift(occurrence.getShift());
                dto.setStartDate(occurrence.getStartDate());
                dto.setEndDate(occurrence.getEndDate());
                dto.setReportId(occurrence.getReport().getId());

                // Mapeamento da lista de occurrenceData
                List<OccurrenceDataRequestDto> dataDtos = occurrence.getOccurrenceData().stream().map(data -> {
                    OccurrenceDataRequestDto dataDto = new OccurrenceDataRequestDto();
                    dataDto.setTypeDescription(data.getDescription());
                    dataDto.setTypeCode(data.getCode());
                    dataDto.setGroupDescription(data.getGroupDescription());
                    dataDto.setGroupCode(data.getGroupCode());
                    dataDto.setResponse(data.getResponse());
                    return dataDto;
                }).collect(Collectors.toList());

                dto.setOccurrenceData(dataDtos);

                return dto;
            }).collect(Collectors.toList());

            // Mapeia todas as descrições e respostas de ocorrencia de dados usa flatMap percorre todas as ocorrências e extrai os OcorrenciaDados.
           /* List<Map<String, Object>> mappedList = occurrencesList.stream()
                    .flatMap(occurrence -> occurrence.getOccurrenceData().stream())
                    .map(data -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put ("description", data.getDescription());
                        map.put ("response", data.getResponse());

                        return map;

                    }) .collect(Collectors.toList());


            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText(MessageState.SUCESSO)
                    .setDetails(Collections.singletonList(occurrencesList))
                    .builder();


        }  catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText(MessageState.ERRO)
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }*/

    /*@Override
    public APIResponse saveOccurrence(OccurrenceDto dto) {

        try {

            Report report = reportRepository.findById(dto.getReportId())
                    .orElseThrow(() -> new RuntimeException("Relatorio nao encontrado com ID :" + dto.getReportId()));

            Occurrence occurrence1 = new Occurrence();
            BeanUtils.copyProperties(dto, occurrence1);
            occurrence1.setReport(report);
            occurrence1.setGraduateService(dto.getGraduateService());
            occurrence1.setShift(dto.getShift());
            occurrence1.setStartDate(dto.getStartDate());
            occurrence1.setEndDate(dto.getEndDate());
            occurrence1.setUserCreate("Admin");

            // Mapeia os occurrenceData do DTO
            if (dto.getOccurrenceData() != null && !dto.getOccurrenceData().isEmpty()) {
                List<OccurrenceData> occurrenceDataList = dto.getOccurrenceData().stream().map(dataDto -> {

                    OccurrenceData data = new OccurrenceData();
                    data.setSelfCode(data.getSelfCode());
                    data.setName(dataDto.getTypeDescription());
                    data.setOrder(data.getOrder());
                    data.setDescription(dataDto.getTypeDescription());
                    data.setCode(dataDto.getTypeCode());
                    data.setGroupCode(dataDto.getGroupCode());
                    data.setGroupDescription(dataDto.getGroupDescription());
                    data.setResponse(dataDto.getResponse());
                    data.setOccurrence(occurrence1);
                    data.setUserCreate("ADMIN");

                    // associa à ocorrência
                    return data;
                }).collect(Collectors.toList());


                occurrence1.setOccurrenceData(occurrenceDataList);
            }

            OccurrenceDto responseDto = new OccurrenceDto();
            responseDto.setGraduateService(dto.getGraduateService());
            responseDto.setShift(dto.getShift());
            responseDto.setStartDate(dto.getStartDate());
            responseDto.setEndDate(dto.getEndDate());
            responseDto.setReportId(dto.getReportId());
            responseDto.setOccurrenceData(dto.getOccurrenceData());


           /* if (occurrence.getOccurrenceData() != null) {
                occurrence.getOccurrenceData().forEach(d -> d.setOccurrence(occurrence));
            }*/

            // Salva os occurrenceData relacionados
           /* if (occurrence1.getOccurrenceData() != null) {
                occurrence1.getOccurrenceData().forEach(data -> {
                    data.setOccurrence(occurrence1);
                })
            }*/

           /* Occurrence savedOccurrence = occurrenceRepository.save(occurrence1);

            // Retorna o DTO com a estrutura correta
            OccurrenceDto responseDto = new OccurrenceDto();
            BeanUtils.copyProperties(savedOccurrence, responseDto);
            responseDto.setId(savedOccurrence.getId());

            if (savedOccurrence.getReport() != null) {
                responseDto.setReportId(savedOccurrence.getReport().getId());
            }

            if (savedOccurrence.getOccurrenceData() != null) {
                List<OccurrenceDataDto> dataDtos = savedOccurrence.getOccurrenceData().stream().map(data -> {
                    OccurrenceDataDto dataDto = new OccurrenceDataDto();
                    BeanUtils.copyProperties(data, dataDto);
                    return dataDto;
                }).collect(Collectors.toList());
                responseDto.setOccurrenceData(dataDtos);
            }

            occurrenceRepository.save(occurrence1);

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setDetails(Collections.singletonList(occurrence1))
                    .setStatusText(MessageState.SUCESSO)
                    .builder();


        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText(MessageState.ERRO)
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }*/

    @Override
    @Transactional
    public APIResponse createOccurrence(String reportId, OccurrenceDto occurrenceDto) {

            try {
                // Verificar se o relatório existe
                Report report = reportRepository.findById(reportId)
                        .orElseThrow(() -> new RuntimeException("Relatório não encontrado com ID: " + reportId));

                // Contar ocorrências existentes para gerar turno automaticamente
                long count = occurrenceRepository.countByReportId(reportId);

                // Gerar turno automaticamente
                String turno = (count + 1) + "º Turno"; // Ex: 1º Turno, 2º Turno, 3º Turno…

                // Criar nova ocorrência
                Occurrence occurrence = new Occurrence();
                BeanUtils.copyProperties(occurrenceDto, occurrence);
                occurrence.setGraduateService(occurrenceDto.getGraduateService());
                occurrence.setStartDate(occurrenceDto.getStartDate());
                occurrence.setEndDate(occurrenceDto.getEndDate());
                occurrence.setShift(occurrenceDto.getShift()); // Ex: "07hr às 15hr"
                occurrence.setTurn(turno);
                occurrence.setUserCreate("ADMIN");
                occurrence.setReport(report);

                occurrenceRepository.save(occurrence);

                // Retorno adequado para o front-end
                Map<String, Object> response = new HashMap<>();
                response.put("id", occurrence.getId());
                response.put("graduateService", occurrence.getGraduateService());
                response.put("data", occurrence.getStartDate());
                response.put("turnoDas", occurrence.getShift());
                response.put("turno", occurrence.getTurn());

                // 6. 🔥 Histórico automático
               /* iHistoryService.autoHistoryOccurrence(
                        report,
                        turno,
                        "OCORRÊNCIA CRIADA",
                        "Nova ocorrência adicionada ao relatório",
                        "ADMIN"
                );*/

                iHistoryService.register(
                        report,
                        "OC",                                    // Código da ação (OC = Ocorrência)
                        "OCORRÊNCIA CRIADA - " + turno,          // Descrição da ação
                        "Nova ocorrência adicionada ao relatório",
                        "ADMIN"                                  // PerformedBy
                );

                return new APIResponse.buildAPIResponse()
                        .setStatus(true)
                        .setStatusText("Ocorrência criada com sucesso para " + turno)
                        .setDetails(Collections.singletonList(turno))
                        .builder();

            } catch (Exception e) {
                return new APIResponse.buildAPIResponse()
                        .setStatus(false)
                        .setStatusText("Erro ao criar ocorrência: " + e.getMessage())
                        .builder();
            }
    }

    @Override
    public APIResponse getOccurrencesByReport(String reportId) {

        try {
            // Verificar se o relatório existe
            /*Report report = reportRepository.findById(reportId)
                    .orElseThrow(() -> new RuntimeException("Relatório não encontrado com ID: " + reportId));*/

            // Buscar ocorrências
            List<Occurrence> occurrences = occurrenceRepository.findByReportId(reportId);

            if (occurrences.isEmpty()) {
                return new APIResponse.buildAPIResponse()
                        .setStatus(true)
                        .setStatusText("Nenhuma ocorrência registrada para este relatório.")
                        .setDetails(Collections.emptyList())
                        .builder();
            }

            // Converter para DTOs simples
            List<Map<String, Object>> dtoList = new ArrayList<>();
            int index = 1;
            for (Occurrence occ : occurrences) {
                Map<String, Object> map = new HashMap<>();
                map.put("n", index++);
                map.put("graduateService", occ.getGraduateService());
                map.put("data", occ.getStartDate());
                map.put("turnoDas", occ.getShift());
                map.put("turno", occ.getTurn());
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
                    .setStatusText("Erro ao buscar ocorrências: " + e.getMessage())
                    .builder();
        }
    }


   /* @Override
    public APIResponse getOccurrencesByReportId(String reportId) {

        try {

            List<Occurrence> occurrences = occurrenceRepository.findByReportId(reportId);

            if (occurrences.isEmpty()) {
                return new APIResponse.buildAPIResponse()
                        .setStatus(false)
                        .setStatusText("Nenhuma ocorrência encontrada para este relatório.")
                        .setDetails(Collections.emptyList())
                        .builder();
            }

            // Lista formatada para retorno
            List<Map<String, Object>> formatted = new ArrayList<>();
            int count = 1;
            for (Occurrence oc : occurrences) {
                Map<String, Object> map = new HashMap<>();

                map.put("numero", count);
                map.put("graduateService", oc.getGraduateService());
                map.put("date", oc.getStartDate()); // pode ser oc.getDate() dependendo do nome no seu modelo
                map.put("shif", oc.getShift());

                // Garante que o turno sempre seja calculado, mesmo que o campo esteja nulo
                String shiftValue;
                if (oc.getShift() == null || oc.getShift().isEmpty()) {
                    shiftValue = count + "º Turno";
                } else {
                    shiftValue = oc.getShift();
                }
                map.put("shift", shiftValue);

                formatted.add(map);
                count++;
            }

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Ocorrências listadas com sucesso")
                    .setDetails(Collections.singletonList(formatted))
                    .builder();

        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao buscar ocorrências: " + e.getMessage())
                    .setDetails(Collections.emptyList())
                    .builder();
        }
    }*/


}




