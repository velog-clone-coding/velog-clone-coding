package velog.sideProject.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import velog.sideProject.common.JsonMethod;
import velog.sideProject.common.initData.DraftPostInitData;
import velog.sideProject.controller.dto.CreateDraftPostDTO;
import velog.sideProject.controller.dto.SearchDraftPostDTO;
import velog.sideProject.service.DraftPostService;


import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@Slf4j
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(DraftPostController.class)
class DraftPostControllerTest {

    private final String api = "/api/draftPost/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DraftPostService draftPostService;

    DraftPostInitData draftPostInitData = new DraftPostInitData();

    //http://localhost:8080/api/myPage/saves (get)
    @Test
    @DisplayName("임시게시글 목록 조회")
    void getDraftPostList() throws Exception {
        //given
        List<SearchDraftPostDTO> searchDraftPostDTOList = draftPostInitData.getSearchDraftPostDTOList();

        given(draftPostService.getDraftPostListWithId(1L))
                .willReturn(searchDraftPostDTOList);

        // andExpect
        MvcResult result = mockMvc.perform(
                        get(api + "saves"))
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



        verify(draftPostService).getDraftPostListWithId(1L);
    }
    //http://localhost:8080/api/myPage/write?postId=? (get)
    @Test
    @DisplayName("임시게시글 정보 조회")
    void getDraftPost() throws Exception {
        //given
        SearchDraftPostDTO searchDraftPostDTO = draftPostInitData.getSearchDraftPostDTO();

        given(draftPostService.getDraftPostWithPostIdMemberId(1L, 1L))
                .willReturn(Optional.ofNullable(searchDraftPostDTO));

        // andExpect
        MvcResult result = mockMvc.perform(
                        get(api + "write").param("postId", "1"))
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



        verify(draftPostService).getDraftPostWithPostIdMemberId(1L, 1L);
    }

    //http://localhost:8080/api/writePage/write/draft (post)
    @Test
    @DisplayName("임시글 생성")
    void createDraftPost() throws Exception {
        //given
        CreateDraftPostDTO createDraftPostDTO = draftPostInitData.getCreateDraftPostDTO();
        SearchDraftPostDTO expectResult = draftPostInitData.getSearchDraftPostDTO();

        BDDMockito.given(draftPostService.createDraftPost(createDraftPostDTO, 1L))
                .willReturn(Optional.ofNullable(expectResult));

        //andExpect
        MvcResult result = mockMvc.perform(
                        post(api + "write")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonMethod.objectToJson(createDraftPostDTO)))
                .andExpect(status().isOk())
                .andReturn();

        // 서버 응답값
        SearchDraftPostDTO responseResult = JsonMethod.jsonToObject(result.getResponse().getContentAsString(), SearchDraftPostDTO.class);

        // 비교
        Assertions.assertThat(expectResult).isEqualTo(responseResult);

        //verify
        Mockito.verify(draftPostService).createDraftPost(createDraftPostDTO, 1L);
    }

    //http://localhost:8080/api/myPage/write?postId=? (delete)
    @Test
    @DisplayName("임시게시글 삭제")
    void deleteDraftPost() throws Exception {
        //given
        SearchDraftPostDTO searchDraftPostDTO = draftPostInitData.getSearchDraftPostDTO();

        given(draftPostService.deleteDraftPostWithPostIdMemberId(1L, 1L)).willReturn(1L);

        // andExpect
        mockMvc.perform(
                        delete(api + "write").param("postId", "1"))
                .andExpect(status().isOk());

        verify(draftPostService).deleteDraftPostWithPostIdMemberId(1L, 1L);
    }

}