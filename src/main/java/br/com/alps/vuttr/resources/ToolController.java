package br.com.alps.vuttr.resources;

import br.com.alps.vuttr.dto.responses.ToolResponseDTO;
import br.com.alps.vuttr.services.IToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/tools")
public class ToolController {

    @Autowired
    private IToolService service;

    @GetMapping
    public ResponseEntity<List<ToolResponseDTO>> getAllTools(@RequestParam(name = "tag", required = false) String tag) {

        List<ToolResponseDTO> tools;
        if(tag == null) {
            tools = service.getAllTools();
            if(tools.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        } else {
            tools = service.getAllByTag(tag);
        }

        return ResponseEntity.ok(tools);
    }

}
