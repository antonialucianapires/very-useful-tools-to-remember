package br.com.alps.vuttr.services;

import br.com.alps.vuttr.config.validacao.errors.validation.exceptions.ObjectNotFoundException;
import br.com.alps.vuttr.config.validacao.errors.validation.exceptions.VttrException;
import br.com.alps.vuttr.domain.Tag;
import br.com.alps.vuttr.domain.Tool;
import br.com.alps.vuttr.dto.request.ToolPostDTO;
import br.com.alps.vuttr.dto.responses.TagResponseDTO;
import br.com.alps.vuttr.dto.responses.ToolResponseDTO;
import br.com.alps.vuttr.repositories.TagRepository;
import br.com.alps.vuttr.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

            List<Tag> tagEntity = tagRepository.findByNameIgnoreCase(tag);

            if(!tagEntity.isEmpty()) {
                List<Tool> toolsEntities = repository.findAllByTagsNameIgnoreCase(tag);
                return toolsEntities.stream().map(ToolResponseDTO::new).collect(Collectors.toList());
            }

            throw new ObjectNotFoundException("Tag not found");

        } catch (ObjectNotFoundException onfe) {
            throw onfe;
        }catch (Exception ex) {
            throw new VttrException(ex.getMessage());
        }
    }

    @Transactional
    @Override
    public ToolResponseDTO postNewTool(ToolPostDTO postDTO) {

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

                newTag = Tag.builder().name(tag.getName().toLowerCase()).id(null).tool(toolEntity).build();
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

    @Override
    public void deleteById(Long id) {

        try {
            Optional<Tool> tool = findById(id);
            if(tool.isPresent()) {
                repository.deleteById(id);
            }

            tool.orElseThrow(() -> new ObjectNotFoundException("Tool not found " + id));
        } catch (Exception exception) {
            throw new VttrException(exception.getMessage());
        }

    }

    @Override
    public Optional<Tool> findById(Long id) {
        return repository.findById(id);
    }

}
