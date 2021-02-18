package br.com.alps.vuttr.services;

import br.com.alps.vuttr.dto.responses.TagResponseDTO;
import br.com.alps.vuttr.dto.responses.ToolResponseDTO;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToolServiceTest {

    @Mock
    private IToolService service;
    private ToolResponseDTO toolDTO;
    private TagResponseDTO tagDTO;

    @Before
    public void setUp() {

        tagDTO = TagResponseDTO.builder().name("calendar").build();

        toolDTO = ToolResponseDTO.builder()
                .id(1L)
                .title("Notion")
                .link("https://notion.so")
                .description("description\": \"All in one tool to organize teams and ideas. Write, plan, collaborate, and get organized.")
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
    public void getAllToolsEmptySucess() {

        Mockito.when(service.getAllTools()).thenReturn(new ArrayList<>());
        List<ToolResponseDTO> tools = service.getAllTools();

        Assert.assertEquals(0, tools.size());
        Assert.assertTrue(tools.isEmpty());

    }

}
