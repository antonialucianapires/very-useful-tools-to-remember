package br.com.alps.vuttr.resources;


import br.com.alps.vuttr.config.validation.exceptions.ObjectNotFoundException;
import br.com.alps.vuttr.config.validation.exceptions.VttrException;
import br.com.alps.vuttr.dto.request.ToolPostDTO;
import br.com.alps.vuttr.dto.responses.ToolResponseDTO;
import br.com.alps.vuttr.services.IToolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/tools")
@Api(value = "Tools")
public class ToolController {

    @Autowired
    private IToolService service;

    @ApiOperation(value = "lists all tools or lists all tools by tag")
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
        } catch (ObjectNotFoundException onfe) {
            return ResponseEntity.status(404).body(onfe.getMessage());
        } catch (VttrException vttrException) {
            return ResponseEntity.status(400).body(vttrException.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }

    @ApiOperation(value = "register a new tool")
    @PostMapping
    public ResponseEntity<?> postNewTool(@RequestBody @Valid ToolPostDTO postDTO) {
        try {
            ToolResponseDTO toolDto = service.postNewTool(postDTO);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(toolDto.getId()).toUri();

            return ResponseEntity.created(uri).body(toolDto);
        } catch (VttrException vttrException) {
            return ResponseEntity.badRequest().body(vttrException.getMessage());
        }
    }

    @ApiOperation(value = "delete tool by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException onfe) {
            return ResponseEntity.status(404).body(onfe.getMessage());
        } catch (VttrException vttrException) {
            return ResponseEntity.status(400).body(vttrException.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }


}
