package br.com.alps.vuttr.services;

import br.com.alps.vuttr.domain.Tool;
import br.com.alps.vuttr.dto.errors.VttrException;
import br.com.alps.vuttr.dto.responses.ToolResponseDTO;
import br.com.alps.vuttr.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToolService implements IToolService{

    @Autowired
    private ToolRepository repository;

    public List<ToolResponseDTO> getAllTools() {
        try {
            List<Tool> toolsEntities = repository.findAll();
            return  toolsEntities.stream().map(ToolResponseDTO::new).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new VttrException(ex.getMessage());
        }
    }

}
