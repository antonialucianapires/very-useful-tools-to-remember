package br.com.alps.vuttr.dto.responses;

import br.com.alps.vuttr.domain.Tool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToolResponseDTO implements Serializable {

    private Long id;
    private String title;
    private String link;
    private String description;
    private List<TagResponseDTO> tags = new ArrayList<>();

    public ToolResponseDTO(Tool toolEntity) {
        this.id = toolEntity.getId();
        this.title = toolEntity.getTitle();
        this.description = toolEntity.getDescription();
        this.link = toolEntity.getLink();
        this.tags = toolEntity.getTags().stream().map(TagResponseDTO::new).collect(Collectors.toList());
    }

}
