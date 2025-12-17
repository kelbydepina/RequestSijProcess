package cv.pn.sitop.business.occurrenceData;



import cv.pn.sitop.business.occurrence.OccurrenceDto;
import cv.pn.sitop.utilities.APIResponse;
import cv.pn.sitop.utilities.MessageState;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/occurrence")
public class OccurrenceDataController {

    private final OccurrenceDataRepository occurrenceDataRepository;

    public OccurrenceDataController(OccurrenceDataRepository occurrenceDataRepository) {
        this.occurrenceDataRepository = occurrenceDataRepository;
    }


    @Operation(summary = "Get Ocorrencia Dados")
    @GetMapping(path = "/{domain}/{selfCode}")
    public ResponseEntity<APIResponse> getProducts(@PathVariable String description, @PathVariable String selfCode) {

        List<OccurrenceData> occurrenceData = occurrenceDataRepository.findAllByDescriptionAndSelfCode(description, selfCode);

        List<Object> objects = occurrenceData.stream()
                .map(occurrenceData1 -> {
                    OccurrenceDto dto = new OccurrenceDto();
                    BeanUtils.copyProperties(occurrenceData1, dto);

                    return dto;
                })
                .collect(Collectors.toList());

        APIResponse response = new APIResponse.buildAPIResponse().setStatus(true)
                .setStatusText(MessageState.SUCESSO)
                .setDetails(objects)
                .builder();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Save Occurrence Data")
    @PostMapping(path = "/{domain}/{selfCode}")
    public ResponseEntity<APIResponse> saveDomain(@RequestBody OccurrenceDataDto dto) {

        try {

            OccurrenceData occurrenceData = new OccurrenceData();
            BeanUtils.copyProperties(dto, occurrenceData);
           occurrenceData.setUserCreate("ADMIN");

            OccurrenceData saved = occurrenceDataRepository.save(occurrenceData);

            OccurrenceDataDto dto1 = new OccurrenceDataDto();
            BeanUtils.copyProperties(saved, dto1);

            APIResponse response = new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText(MessageState.SUCESSO)
                    .setDetails(List.of(dto1))
                    .builder();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            APIResponse response = new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao criar ocorrência: " + e.getMessage())
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
            return ResponseEntity.badRequest().body(response);
        }

    }
}
