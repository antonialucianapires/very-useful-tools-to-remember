package br.com.alps.vuttr.dto.request;

import br.com.alps.vuttr.dto.responses.TagResponseDTO;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ToolPostDTO implements Serializable {

    @NotNull(message = "the field cannot be null")
    @Size(min = 1, max = 15, message = "enter between 1 and 15 characters")
    private String title;
    @NotNull(message = "the field cannot be null")
    @Size(min = 1, max = 200, message = "enter between 1 and 200 characters")
    private String link;
    @NotNull(message = "the field cannot be null")
    @Size(min = 1, max = 2000, message = "enter between 1 and 2000 characters")
    private String description;
    @NotNull(message = "the field cannot be null")
    @NotEmpty(message = "the field cannot be empty")
    private List<TagResponseDTO> tags;
}
