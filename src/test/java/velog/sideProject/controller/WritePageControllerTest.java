package velog.sideProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import velog.sideProject.config.JsonMethod;
import velog.sideProject.config.initData.DraftPostInitData;
import velog.sideProject.controller.dto.CreateDraftPostDTO;
import velog.sideProject.controller.dto.SearchDraftPostDTO;
import velog.sideProject.service.WritePageService;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@WebMvcTest(WritePageController.class)
class WritePageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    WritePageService writePageService;

    DraftPostInitData draftPostInitData = new DraftPostInitData();

    //http://localhost:8080/api/writePage/write/draft (post)
    @Test
    @DisplayName("임시글 생성")
    void createDraftPost() throws Exception {
        //given
        CreateDraftPostDTO createDraftPostDTO = CreateDraftPostDTO.builder().build();
        SearchDraftPostDTO expectResult = draftPostInitData.getSearchDraftPostDTO();

        BDDMockito.given(writePageService.createDraftPost(createDraftPostDTO, 1L))
                .willReturn(Optional.ofNullable(expectResult));

        //andExpect
        MvcResult result = mockMvc.perform(
                        post("/api/writePage/write/draft")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonMethod.objectToJson(createDraftPostDTO)))
                .andExpect(status().isOk())
                .andReturn();

        // 서버 응답값
        SearchDraftPostDTO responseResult = JsonMethod.jsonToObject(result.getResponse().getContentAsString(), SearchDraftPostDTO.class);

        // 비교
        Assertions.assertThat(expectResult).isEqualTo(responseResult);

        //verify
        Mockito.verify(writePageService).createDraftPost(createDraftPostDTO, 1L);
    }
}