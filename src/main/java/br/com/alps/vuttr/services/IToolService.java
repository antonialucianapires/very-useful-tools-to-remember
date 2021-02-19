package br.com.alps.vuttr.services;

import br.com.alps.vuttr.domain.Tool;
import br.com.alps.vuttr.dto.request.ToolPostDTO;
import br.com.alps.vuttr.dto.responses.ToolResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IToolService {
    List<ToolResponseDTO> getAllTools();

    List<ToolResponseDTO> getAllByTag(String tag);

    ToolResponseDTO postNewTool(ToolPostDTO postDTO);

    void deleteById(Long id);

    Optional<Tool> findById(Long id);
}
