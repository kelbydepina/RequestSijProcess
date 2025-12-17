/*package cv.pn.sitop.business.regiao;


import cv.pn.sitop.utilities.APIResponse;
import cv.pn.sitop.utilities.MessageState;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegiaoService implements IRegiaoService {

    public final RegiaoRepository regiaoRepository;

    public RegiaoService(RegiaoRepository regiaoRepository) {
        this.regiaoRepository = regiaoRepository;
    }

    @Override
    public APIResponse saveRegiao(RegiaoDto dto) {

        try {

            Regiao regiao = new Regiao();

            BeanUtils.copyProperties(dto, regiao);
            regiao.setNome(dto.getNome());
            regiao.setTipo(dto.getTipo());
            regiao.setSigla(dto.getSigla());
            regiao.setDescricao(dto.getDescricao());
            regiao.setCodigo(dto.getCodigo());
            regiao.setUserCreate("Admin");

            regiaoRepository.save(regiao);

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setDetails(Collections.singletonList(regiao))
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
    public APIResponse getAllRegiao() {

        try {

            List<RegiaoDto> regioes = regiaoRepository.findAll().stream().map(regiao -> {
                RegiaoDto dto = new RegiaoDto();
                BeanUtils.copyProperties(regiao, dto);
                dto.setNome(regiao.getNome());
                dto.setTipo(regiao.getTipo());
                dto.setSigla(regiao.getSigla());
                dto.setDescricao(regiao.getDescricao());
                dto.setCodigo(regiao.getCodigo());
                return dto;

            }).collect(Collectors.toList());




           // return new APIResponse(true," Lista dos Regios com Sucesso", regioes);

            if (regioes.isEmpty()) {
                return new APIResponse.buildAPIResponse()
                        .setStatus(false)
                        .setStatusText("Nenhuma reigao encontrada")
                        .setDetails(Collections.emptyList())
                        .builder();
            }

            return new APIResponse.buildAPIResponse()
                    .setStatus(true)
                    .setStatusText("Lista de regioes com sucesso")
                    .setDetails(Collections.singletonList(regioes))
                    .builder();


        } catch (Exception e) {
            return new APIResponse.buildAPIResponse()
                    .setStatus(false)
                    .setStatusText("Erro ao listar regioes")
                    .setDetails(Collections.singletonList(e.getMessage()))
                    .builder();
        }

        }


}*/
