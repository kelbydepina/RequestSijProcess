package cv.pn.sitop.business.domain;



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
public class DomainController {

    private final DomainRepository domainRepository;

    public DomainController(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @Operation(summary = "Get Domain")
    @GetMapping(path = "/{domain}/{selfCode}")
    public ResponseEntity<APIResponse> getProducts(@PathVariable String domain, @PathVariable String selfCode) {

        List<Domain> domains = domainRepository.findAllByDomainAndSelfCode(domain, selfCode);

        List<Object> objects = domains.stream()
                .map(domain1 -> {
                    DomainDto dto = new DomainDto();
                    BeanUtils.copyProperties(domain1, dto);

                    return dto;
                })
                .collect(Collectors.toList());

        APIResponse response = new APIResponse.buildAPIResponse().setStatus(true)
                .setStatusText(MessageState.SUCESSO)
                .setDetails(objects)
                .builder();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Save Domain")
    @PostMapping(path = "/{domain}/{selfCode}")
    public ResponseEntity<APIResponse> saveDomain(@RequestBody DomainDto dto) {

        try {

           Domain domain = new Domain();
            BeanUtils.copyProperties(dto, domain);
            domain.setUserCreate("ADMIN");

           Domain saved = domainRepository.save(domain);

            // Converter de volta para DTO (opcional)
           DomainDto dto1 = new DomainDto();
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
                    .setStatusText(MessageState.ERRO)
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
            return ResponseEntity.badRequest().body(response);
        }

    }
}
