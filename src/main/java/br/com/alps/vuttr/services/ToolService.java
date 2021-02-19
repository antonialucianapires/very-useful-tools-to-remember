package br.com.alps.vuttr.services;

import br.com.alps.vuttr.domain.Tag;
import br.com.alps.vuttr.domain.Tool;
import br.com.alps.vuttr.dto.errors.VttrException;
import br.com.alps.vuttr.dto.request.ToolPostDTO;
import br.com.alps.vuttr.dto.responses.TagResponseDTO;
import br.com.alps.vuttr.dto.responses.ToolResponseDTO;
import br.com.alps.vuttr.repositories.TagRepository;
import br.com.alps.vuttr.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToolService implements IToolService {

    @Autowired
    private ToolRepository repository;

    @Autowired
    private TagRepository tagRepository;

    public List<ToolResponseDTO> getAllTools() {
        try {
            List<Tool> toolsEntities = repository.findAll();
            return toolsEntities.stream().map(ToolResponseDTO::new).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new VttrException(ex.getMessage());
        }
    }

    @Override
    public List<ToolResponseDTO> getAllByTag(String tag) {
        try {
            List<Tool> toolsEntities = repository.findAllByTagsName(tag);
            return toolsEntities.stream().map(ToolResponseDTO::new).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new VttrException(ex.getMessage());
        }
    }

    @Transactional
    public ToolResponseDTO postNewDTO(ToolPostDTO postDTO) {

        try {
            Tool toolEntity = Tool.builder()
                    .id(null)
                    .title(postDTO.getTitle())
                    .link(postDTO.getLink())
                    .description(postDTO.getDescription())
                    .build();

            List<Tag> tags = new ArrayList<>();
            Tag newTag;
            for (TagResponseDTO tag : postDTO.getTags()) {
                newTag = Tag.builder().name(tag.getName()).tools(Collections.singletonList(toolEntity)).id(null).build();
                newTag = tagRepository.save(newTag);
                tags.add(newTag);
            }

            toolEntity.setTags(tags);
            toolEntity = repository.saveAndFlush(toolEntity);
            return new ToolResponseDTO(toolEntity);

        } catch (Exception ex) {
            throw new VttrException("Failed to save tool. Ex: " + ex.getMessage());
        }


    }

}
