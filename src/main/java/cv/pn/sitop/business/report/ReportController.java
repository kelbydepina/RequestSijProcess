package cv.pn.sitop.business.report;

import cv.pn.sitop.business.occurrence.IOccurrenceServices;
import cv.pn.sitop.utilities.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("1.0.0/report")
public class ReportController {

    private final IReportService iReportService;

    private final IOccurrenceServices iOccurrenceServices;


    public ReportController(IReportService iReportService, IOccurrenceServices iOccurrenceServices) {
        this.iReportService = iReportService;

        this.iOccurrenceServices = iOccurrenceServices;
    }


    @Operation(summary = "Save Report")
    @PostMapping()
    public ResponseEntity<APIResponse> saveReport(@RequestBody ReportDto dto) {

        return ResponseEntity.ok(iReportService.saveReport(dto));
    }

    @Operation(summary = "Get All Report")
    @GetMapping()
    public ResponseEntity<APIResponse> GetAllReport() {

        return ResponseEntity.ok(iReportService.GetAllReport());
    }

    @Operation(summary = "Get ID Report")
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> saveReportId(@PathVariable String id) {

        return ResponseEntity.ok(iReportService.getReportId(id));
    }

    @Operation(summary = "Update ID Report")
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateReport(@PathVariable String id, @RequestBody ReportDto dto) {

        return ResponseEntity.ok(iReportService.updateReport(id, dto));
    }

    /*@PutMapping("/{id}/next-phase")
    public ResponseEntity<APIResponse> advancePhase(@PathVariable String id) {
        return ResponseEntity.ok(iReportService.updatePhase(id));
    }*/

    @Operation(summary = "DELETE ID Report")
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delectReport(@PathVariable String id) {

        return ResponseEntity.ok(iReportService.delectReport(id));
    }


   /* @Operation(summary = "Listar ocorrências associadas a um relatório com turnos automáticos")
    @GetMapping("/{reportId}/occurrences")
    public ResponseEntity<APIResponse> getOccurrencesByReportId(@PathVariable String reportId) {
        APIResponse response = iOccurrenceServices.getOccurrencesByReportId(reportId);
        return ResponseEntity.ok(response);
    }*/


}
