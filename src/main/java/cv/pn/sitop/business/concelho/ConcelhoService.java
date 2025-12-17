/*package cv.pn.sitop.business.concelho;


import cv.pn.sitop.utilities.APIResponse;
import cv.pn.sitop.utilities.MessageState;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcelhoService implements IConcelhoService{

    public final ConcelhoRepository concelhoRepository;

    public ConcelhoService(ConcelhoRepository concelhoRepository) {
        this.concelhoRepository = concelhoRepository;
    }

    @Override
    public APIResponse saveConcelho(ConcelhoDto dto) {

        try {

            Concelho concelho = new Concelho();
            BeanUtils.copyProperties(dto, concelho);
            concelho.setNome(concelho.getNome());
            concelho.setCodigo(concelho.getCodigo());
            concelho.setRegiaoId(concelho.getRegiaoId());
            concelho.setDescricao(concelho.getDescricao());
            concelho.setUserCreate("Admin");

            concelhoRepository.save(concelho);

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setDetails(Collections.singletonList(concelho))
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
    public APIResponse getAllConcelho() {

        try {

            List<ConcelhoDto> concelhos = concelhoRepository.findAll().stream().map(concelho -> {
                ConcelhoDto dto = new ConcelhoDto();
                BeanUtils.copyProperties(concelho, dto);
                dto.setNome(concelho.getNome());
                dto.setCodigo(concelho.getCodigo());
                dto.setRegiaoId(concelho.getRegiaoId());
                dto.setDescricao(concelho.getDescricao());
                return dto;



            }).collect(Collectors.toList());

            if (concelhos.isEmpty()) {
                return new APIResponse.buildAPIResponse()
                        .setStatus(false)
                        .setStatusText("Nenhum concelho encontrada")
                        .setDetails(Collections.emptyList())
                        .builder();
            }

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Lista de concelhos com sucesso")
                    .setDetails(Collections.singletonList(concelhos))
                    .builder();

        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao listar concelhos")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }
    }
}*/
