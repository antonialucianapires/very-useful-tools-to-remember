package br.com.alps.vuttr.dto.request;

import br.com.alps.vuttr.dto.responses.TagResponseDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ToolPostDTO implements Serializable {

    private String title;
    private String link;
    private String description;
    private List<TagResponseDTO> tags;
}
