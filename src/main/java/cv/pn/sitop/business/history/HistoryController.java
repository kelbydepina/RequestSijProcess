package cv.pn.sitop.business.history;


import cv.pn.sitop.utilities.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/1.0.0/historico")
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("/{reportId}")
    public ResponseEntity<APIResponse> saveHistory(
            @RequestBody HistoryDto dto) {
        return ResponseEntity.ok(historyService.saveHistory(dto));
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<APIResponse> getHistoryByReport(@PathVariable String reportId) {
        return ResponseEntity.ok(historyService.getHistoryByReport(reportId));
    }
}
