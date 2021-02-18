package br.com.alps.vuttr.resources;

import br.com.alps.vuttr.dto.responses.ToolResponseDTO;
import br.com.alps.vuttr.services.IToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/tools")
public class ToolController {

    @Autowired
    private IToolService service;

    @GetMapping
    public ResponseEntity<List<ToolResponseDTO>> getAllTools() {

        List<ToolResponseDTO> tools = service.getAllTools();
        if(tools.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tools);
    }

}
