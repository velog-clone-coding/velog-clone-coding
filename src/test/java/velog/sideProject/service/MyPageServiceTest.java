package velog.sideProject.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import velog.sideProject.controller.dto.SearchDraftPostDTO;
import velog.sideProject.entity.drfatpost.DraftPost;
import velog.sideProject.entity.drfatpost.DraftTag;
import velog.sideProject.common.initData.DraftPostInitData;
import velog.sideProject.repository.draftpost.jpa.DraftPostRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest(classes = {MyPageService.class, DraftPostRepository.class})
class MyPageServiceTest {

    @Autowired
    MyPageService myPageService;

    @MockBean
    DraftPostRepository draftPostRepository;

    DraftPostInitData draftPostInitData = new DraftPostInitData();

    @Test
    @DisplayName("멤버아이디로 임시게시글 조회")
    void getDraftPostWithId() throws Exception {
        //given
        List<DraftPost> draftPostList = draftPostInitData.getDraftPostList();

        BDDMockito.given(draftPostRepository.findByMember_MemberId(1L))
                .willReturn(draftPostList);

        List<SearchDraftPostDTO> expectResult = draftPostList.stream()
                .map(draftPost -> SearchDraftPostDTO.toDTO(draftPost, getDraftTagListWithDraftPostId(draftPost)))
                .toList();

        //when
        List<SearchDraftPostDTO> result = myPageService.getDraftPostListWithId(1L);

        Assertions.assertThat(result).isEqualTo(expectResult);

        //verfiy
        Mockito.verify(draftPostRepository).findByMember_MemberId(1L);
    }

    @Test
    @DisplayName("임시글 정보 조회")
    void getDraftPostWithPostIdMemberId() throws Exception {
        DraftPost draftPost = draftPostInitData.getDraftPost();
        //given
        BDDMockito.given(draftPostRepository.findByDraftPostIdAndMember_MemberId(1L, 1L))
                .willReturn(Optional.ofNullable(draftPost));

        //when
        SearchDraftPostDTO result = myPageService.getDraftPostWithPostIdMemberId(1L, 1L).get();

        Assertions.assertThat(draftPost.getDraftPostId()).isEqualTo(result.getPostId());

        //verfiy
        Mockito.verify(draftPostRepository).findByDraftPostIdAndMember_MemberId(1L, 1L);
    }

    @Test
    @DisplayName("임시글 삭제")
    void deleteDraftPostWithPostIdMemberId() throws Exception {
        DraftPost draftPost = draftPostInitData.getDraftPost();
        //given
        BDDMockito.given(draftPostRepository.deleteByDraftPostIdAndMember_MemberId(1L, 1L))
                .willReturn(1L);

        //when
        Long result = myPageService.deleteDraftPostWithPostIdMemberId(1L, 1L);

        Assertions.assertThat(result).isEqualTo(1L);

        //verfiy
        Mockito.verify(draftPostRepository).deleteByDraftPostIdAndMember_MemberId(1L, 1L);
    }

    // DraftPost에 맞는 tag 찾는 메서드
    private List<String> getDraftTagListWithDraftPostId(DraftPost draftPost) {

        // DraftPost에 맞는 DraftTagList 찾기
        List<DraftTag> draftTagList = draftPost.getDraftTagList();

        return draftTagList.stream()
                .map(DraftTag::getDraftTagString)
                .toList();

    }
}