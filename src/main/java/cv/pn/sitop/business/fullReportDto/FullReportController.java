package cv.pn.sitop.business.fullReportDto;


import cv.pn.sitop.utilities.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/1.0.0/full-report")
public class FullReportController {

    private final IFullReportService iFullReportService;

    public FullReportController(IFullReportService iFullReportService) {
        this.iFullReportService = iFullReportService;
    }

    @Operation(summary = "Salvar Report completo com ocorrência e dados de ocorrência")
    @PostMapping
    public ResponseEntity<APIResponse> saveFullReport(@RequestBody FullReportRequestDto dto) {
        return ResponseEntity.ok(iFullReportService.saveFullReport(dto));
    }

    @Operation(summary = "Get ID fullReport")
    @GetMapping("/{reportId}")
    public ResponseEntity<APIResponse> getFullReportById(@PathVariable String reportId) {

        return ResponseEntity.ok(iFullReportService.getFullReportById(reportId));
    }

    /*@Operation(summary = "Obter todos os relatórios completos")
    @GetMapping
    public ResponseEntity<APIResponse> getAllFullReports() {
        return ResponseEntity.ok(iFullReportService.getAllFullReports());
    }*/

}
