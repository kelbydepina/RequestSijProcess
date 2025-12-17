/*package cv.pn.sitop.business.regiao;


import cv.pn.sitop.utilities.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/1.0.0/regioes")
public class RegiaoController {

    public final IRegiaoService iRegiaoService;

    public RegiaoController(IRegiaoService iRegiaoService) {
        this.iRegiaoService = iRegiaoService;
    }


    @Operation(summary = "Save Regiao")
    @PostMapping()
    public ResponseEntity<APIResponse> saveRegiao(@RequestBody RegiaoDto dto) {
        return ResponseEntity.ok(iRegiaoService.saveRegiao(dto));
    }

    @Operation(summary = "Get All Regiao")
    @GetMapping()
    public ResponseEntity<APIResponse> getAllRegiao() {
        return ResponseEntity.ok(iRegiaoService.getAllRegiao());
    }
}*/
