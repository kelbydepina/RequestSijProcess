package cv.pn.sitop.business.occurrence;


import cv.pn.sitop.utilities.APIResponse;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/1.0.0/occurrence")
public class OccurrenceController {

    public final IOccurrenceServices iOccurenceServices;

    public final OccurrenceRepository occurrenceRepository;

    public OccurrenceController(IOccurrenceServices iOccurenceServices, OccurrenceRepository occurrenceRepository) {
        this.iOccurenceServices = iOccurenceServices;
        this.occurrenceRepository = occurrenceRepository;
    }


    /*@Operation(summary = "Get ID Ocorrencias")
    @GetMapping("/{relatorioId}")
    public ResponseEntity<APIResponse> getOccurrenceId(@PathVariable("relatorioId") String reportId){

        return ResponseEntity.ok(iOccurenceServices.getOccurrence(reportId));
    }

    @Operation(summary = "Get All Ocorrencias")
    @GetMapping()
    public ResponseEntity<APIResponse> getAllOccurrence(){

        return ResponseEntity.ok(iOccurenceServices.getAllOccurrence());
    }



    @Operation(summary = "Save Ocorrencias")
    @PostMapping()
    public ResponseEntity<APIResponse> saveOccurrence(
                                                      @RequestBody OccurrenceDto dto) {

       return ResponseEntity.ok(iOccurenceServices.saveOccurrence(dto));
    }*/

    @Operation(summary = "Criar ocorrência automaticamente conforme o turno")
    @PostMapping("/{reportId}/create")
    public ResponseEntity<APIResponse> createOccurrence(
            @PathVariable String reportId,
            @RequestBody OccurrenceDto occurrenceDto) {
        return ResponseEntity.ok(iOccurenceServices.createOccurrence(reportId, occurrenceDto));
    }

    @Operation(summary = "Listar todas as ocorrências de um relatório")
    @GetMapping("/{reportId}")
    public ResponseEntity<APIResponse> getOccurrencesByReport(@PathVariable String reportId) {
        return ResponseEntity.ok(iOccurenceServices.getOccurrencesByReport(reportId));
    }

}
