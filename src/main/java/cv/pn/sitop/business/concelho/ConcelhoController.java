/*package cv.pn.sitop.business.concelho;

import cv.pn.sitop.utilities.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/1.0.0/concelhos")
public class ConcelhoController {

    public final IConcelhoService iConcelhoService;

    public ConcelhoController(IConcelhoService iConcelhoService) {
        this.iConcelhoService = iConcelhoService;
    }

    @Operation(summary = "Save Concelho")
    @PostMapping()
    public ResponseEntity<APIResponse> saveConcelho(@RequestBody ConcelhoDto dto) {

        return ResponseEntity.ok(iConcelhoService.saveConcelho(dto));
    }

    @Operation(summary = "Get All Concelho")
    @GetMapping()
    public ResponseEntity<APIResponse> getAllConcelho(){

        return ResponseEntity.ok(iConcelhoService.getAllConcelho());
    }

}*/
