package velog.sideProject.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import velog.sideProject.common.initData.DraftPostInitData;
import velog.sideProject.controller.dto.SearchDraftPostDTO;
import velog.sideProject.service.MyPageService;


import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@Slf4j
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(MyPageController.class)
class MyPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MyPageService myPageService;

    DraftPostInitData draftPostInitData = new DraftPostInitData();

    //http://localhost:8080/api/myPage/saves (get)
    @Test
    @DisplayName("임시게시글 목록 조회")
    void getDraftPostList() throws Exception {
        //given
        List<SearchDraftPostDTO> searchDraftPostDTOList = draftPostInitData.getSearchDraftPostDTOList();

        given(myPageService.getDraftPostListWithId(1L))
                .willReturn(searchDraftPostDTOList);

        // andExpect
        MvcResult result = mockMvc.perform(
                        get("/api/myPage/saves"))
                .andExpect(status().isOk())
//                .andDo(print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        List<SearchDraftPostDTO> expectResult = objectMapper.readValue(content, new TypeReference<List<SearchDraftPostDTO>>() {});


        Assertions.assertThat(searchDraftPostDTOList)
                .describedAs("expect result check")
                .isEqualTo(expectResult);



        verify(myPageService).getDraftPostListWithId(1L);
    }
    //http://localhost:8080/api/myPage/write?postId=? (get)
    @Test
    @DisplayName("임시게시글 정보 조회")
    void getDraftPost() throws Exception {
        //given
        SearchDraftPostDTO searchDraftPostDTO = draftPostInitData.getSearchDraftPostDTO();

        given(myPageService.getDraftPostWithPostIdMemberId(1L, 1L))
                .willReturn(Optional.ofNullable(searchDraftPostDTO));

        // andExpect
        MvcResult result = mockMvc.perform(
                        get("/api/myPage/write").param("postId", "1"))
                .andExpect(status().isOk())
//                .andDo(print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        SearchDraftPostDTO expectResult = objectMapper.readValue(content, new TypeReference<SearchDraftPostDTO>() {});


        Assertions.assertThat(searchDraftPostDTO)
                .describedAs("expect result check")
                .isEqualTo(expectResult);



        verify(myPageService).getDraftPostWithPostIdMemberId(1L, 1L);
    }

    //http://localhost:8080/api/myPage/write?postId=? (delete)
    @Test
    @DisplayName("임시게시글 삭제")
    void deleteDraftPost() throws Exception {
        //given
        SearchDraftPostDTO searchDraftPostDTO = draftPostInitData.getSearchDraftPostDTO();

        given(myPageService.deleteDraftPostWithPostIdMemberId(1L, 1L)).willReturn(1L);

        // andExpect
        mockMvc.perform(
                        delete("/api/myPage/write").param("postId", "1"))
                .andExpect(status().isOk());

        verify(myPageService).deleteDraftPostWithPostIdMemberId(1L, 1L);
    }

}