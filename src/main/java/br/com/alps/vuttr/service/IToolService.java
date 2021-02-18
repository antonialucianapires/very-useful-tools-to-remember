package br.com.alps.vuttr.service;

import br.com.alps.vuttr.dto.responses.ToolResponseDTO;

import java.util.List;

public interface IToolService {
    List<ToolResponseDTO> getAllTools();
}
