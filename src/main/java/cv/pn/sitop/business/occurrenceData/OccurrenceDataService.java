package cv.pn.sitop.business.occurrenceData;


import cv.pn.sitop.business.occurrence.OccurrenceDto;
import cv.pn.sitop.utilities.APIResponse;
import cv.pn.sitop.utilities.MessageState;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OccurrenceDataService implements IOccurrenceDataService {

    private final OccurrenceDataRepository occurrenceDataRepository;

    public OccurrenceDataService(OccurrenceDataRepository occurrenceDataRepository) {
        this.occurrenceDataRepository = occurrenceDataRepository;
    }



    @Override
    public APIResponse saveOccurrence(OccurrenceDto dto) {

        try {
            OccurrenceData occurrenceData = new OccurrenceData();
            BeanUtils.copyProperties(dto, occurrenceData);

            occurrenceDataRepository.save(occurrenceData);

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setDetails(Collections.singletonList(occurrenceData))
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
    public APIResponse getAllOccurrence() {

        try {

            List<OccurrenceDto> occurrence = occurrenceDataRepository.findAll().stream().map(occurrence1 -> {

                OccurrenceDto dto = new OccurrenceDto();
                BeanUtils.copyProperties(occurrence1, dto);

                return dto;
            }).collect(Collectors.toList());

            if (occurrence.isEmpty()) {
                return new APIResponse.buildAPIResponse()
                        .setStatus(false)
                        .setStatusText("Nenhuma ocorrencia encontrada")
                        .setDetails(Collections.emptyList())
                        .builder();
            }

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Lista de ocorrencias com sucesso")
                    .setDetails(Collections.singletonList(occurrence))
                    .builder();


        }  catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao listar ocorrencias")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }

    @Override
    public void save(OccurrenceData data) {
        occurrenceDataRepository.save(data);
    }


}
