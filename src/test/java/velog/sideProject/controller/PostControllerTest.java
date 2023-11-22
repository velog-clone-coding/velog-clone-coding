package velog.sideProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import velog.sideProject.common.JsonMethod;
import velog.sideProject.common.initData.DraftPostInitData;
import velog.sideProject.controller.dto.CreateDraftPostDTO;
import velog.sideProject.controller.dto.CreatePostDTO;
import velog.sideProject.controller.dto.SearchDraftPostDTO;
import velog.sideProject.controller.dto.SearchPostDTO;
import velog.sideProject.service.PostService;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(PostController.class)
class PostControllerTest {

    private final String api = "/api/post/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PostService postService;

    DraftPostInitData draftPostInitData = new DraftPostInitData();



    @Test
    @DisplayName("게시글 생성")
    void createPost() throws Exception {
        //given
        Long memberId = 1L;
        CreatePostDTO createPostDTO = CreatePostDTO.builder().build();
        SearchPostDTO expectResult = SearchPostDTO.builder().postId(1L).build();

        BDDMockito.given(postService.createPost(createPostDTO, memberId))
                .willReturn(Optional.ofNullable(expectResult));

        MvcResult result = mockMvc.perform(
                        post(api + "write")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonMethod.objectToJson(createPostDTO)))
                .andExpect(status().isOk())
                .andReturn();

        // 서버 응답값
        SearchPostDTO responseResult = JsonMethod.jsonToObject(result.getResponse().getContentAsString(), SearchPostDTO.class);

        // 비교
        Assertions.assertThat(expectResult).isEqualTo(responseResult);

        //verify
        Mockito.verify(postService).createPost(createPostDTO, memberId);
    }
}