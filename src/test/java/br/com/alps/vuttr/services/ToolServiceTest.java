package br.com.alps.vuttr.services;

import br.com.alps.vuttr.domain.Tag;
import br.com.alps.vuttr.dto.errors.VttrException;
import br.com.alps.vuttr.dto.request.ToolPostDTO;
import br.com.alps.vuttr.dto.responses.TagResponseDTO;
import br.com.alps.vuttr.dto.responses.ToolResponseDTO;
import br.com.alps.vuttr.repositories.TagRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolServiceTest {

    @Mock
    private IToolService service;

    @Mock
    private TagRepository tagRepository;
    private ToolResponseDTO toolDTO;
    private TagResponseDTO tagDTO;
    private Tag tagEntity;
    private ToolPostDTO toolPostDTO;

    @Before
    public void setUp() {

        tagEntity = Tag.builder().id(1L).name("calendar").build();

        tagDTO = TagResponseDTO.builder().name("calendar").build();

        toolDTO = ToolResponseDTO.builder()
                .id(1L)
                .title("hotel")
                .link("https://github.com/typicode/hotel")
                .description("Local app manager. Start apps within your browser, developer tool with local .localhost domain and https out of the box.")
                .tags(Collections.singletonList(tagDTO))
                .build();

        toolPostDTO = ToolPostDTO.builder()
                .title("hotel")
                .link("https://github.com/typicode/hotel")
                .description("Local app manager. Start apps within your browser, developer tool with local .localhost domain and https out of the box.")
                .tags(Collections.singletonList(tagDTO))
                .build();


    }

    @Test
    public void getAllToolsSucess() {

        Mockito.when(service.getAllTools()).thenReturn(Collections.singletonList(toolDTO));
        List<ToolResponseDTO> tools = service.getAllTools();

        Assert.assertEquals(1, tools.size());
        Assert.assertTrue(tools.contains(toolDTO) && service.getAllTools().contains(toolDTO));

    }

    @Test
    public void getAllToolsIsEmptySucess() {

        Mockito.when(service.getAllTools()).thenReturn(new ArrayList<>());
        List<ToolResponseDTO> tools = service.getAllTools();

        Assert.assertEquals(0, tools.size());
        Assert.assertTrue(tools.isEmpty());

    }

    @Test
    public void gettAllToolsByTag() {
        Mockito.when(service.getAllByTag("calendar")).thenReturn(Collections.singletonList(toolDTO));
        List<ToolResponseDTO> tools = service.getAllByTag("calendar");

        Assert.assertEquals(1, tools.size());
        Assert.assertEquals(1, tools.stream().map(tool -> tool.getTags().stream().map(tag -> tag.getName().equals("calendar"))).collect(Collectors.toList()).size());

    }

    @Test(expected = VttrException.class)
    public void getAllByTagNotFound() {
        Mockito.when(service.getAllByTag("example")).thenThrow(VttrException.class);
        service.getAllByTag("example");
    }

    @Test
    public void getAllByTagToolNotFound() {
        Mockito.when(service.getAllByTag("java")).thenReturn(new ArrayList<>());
        List<ToolResponseDTO> tools = service.getAllByTag("java");

        Assert.assertEquals(0, tools.size());
        Assert.assertTrue(tools.isEmpty());
    }

    @Test
    public void postNewToolSucess() {
        Mockito.when(service.postNewTool(toolPostDTO)).thenReturn(toolDTO);
        ToolResponseDTO newTool = service.postNewTool(toolPostDTO);

        Assert.assertEquals(toolDTO, newTool);
        Assert.assertEquals(1L, (long) newTool.getId());
    }

}
