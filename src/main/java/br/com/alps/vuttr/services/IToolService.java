package br.com.alps.vuttr.services;

import br.com.alps.vuttr.dto.request.ToolPostDTO;
import br.com.alps.vuttr.dto.responses.ToolResponseDTO;

import java.util.List;

public interface IToolService {
    List<ToolResponseDTO> getAllTools();

    List<ToolResponseDTO> getAllByTag(String tag);

    ToolResponseDTO postNewDTO(ToolPostDTO postDTO);
}
