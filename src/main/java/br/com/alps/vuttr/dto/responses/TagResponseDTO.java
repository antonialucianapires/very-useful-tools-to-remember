package br.com.alps.vuttr.dto.responses;

import br.com.alps.vuttr.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagResponseDTO implements Serializable {

    private String name;

    public TagResponseDTO(Tag tagEntity) {
        this.name = tagEntity.getName();
    }

}
