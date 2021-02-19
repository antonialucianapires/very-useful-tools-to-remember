package br.com.alps.vuttr.resources;

import br.com.alps.vuttr.dto.errors.VttrException;
import br.com.alps.vuttr.dto.request.ToolPostDTO;
import br.com.alps.vuttr.dto.responses.ToolResponseDTO;
import br.com.alps.vuttr.services.IToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/tools")
public class ToolController {

    @Autowired
    private IToolService service;

    @GetMapping
    public ResponseEntity<?> getAllTools(@RequestParam(name = "tag", required = false) String tag) {

        try {
            List<ToolResponseDTO> tools;
            if (tag == null) {
                tools = service.getAllTools();
                if (tools.isEmpty()) {
                    return ResponseEntity.noContent().build();
                }
            } else {
                tools = service.getAllByTag(tag);
            }
            return ResponseEntity.ok(tools);
        } catch (VttrException vttrException) {
            return ResponseEntity.badRequest().body(vttrException.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> postNewTool(@RequestBody ToolPostDTO postDTO) {
        try {
            ToolResponseDTO toolDto = service.postNewDTO(postDTO);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(toolDto.getId()).toUri();

            return ResponseEntity.created(uri).body(toolDto);
        } catch (VttrException vttrException) {
            return ResponseEntity.badRequest().body(vttrException.getMessage());
        }
    }


}
