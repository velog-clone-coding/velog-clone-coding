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
import velog.sideProject.controller.dto.CreateDraftPostDTO;
import velog.sideProject.controller.dto.SearchDraftPostDTO;
import velog.sideProject.common.initData.DraftPostInitData;

import velog.sideProject.entity.Member;
import velog.sideProject.entity.drfatPost.DraftPost;
import velog.sideProject.entity.drfatPost.DraftTag;
import velog.sideProject.repository.draftpost.jpa.DraftPostRepository;
import velog.sideProject.repository.draftpost.jpa.DraftTagRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest(classes = {DraftPostService.class, DraftPostRepository.class})
class DraftPostServiceTest {

    @Autowired
    DraftPostService draftPostService;

    @MockBean
    DraftPostRepository draftPostRepository;

    @MockBean
    DraftTagRepository draftTagRepository;

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
        List<SearchDraftPostDTO> result = draftPostService.getDraftPostListWithId(1L);

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
        SearchDraftPostDTO result = draftPostService.getDraftPostWithPostIdMemberId(1L, 1L).get();

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
        Long result = draftPostService.deleteDraftPostWithPostIdMemberId(1L, 1L);

        Assertions.assertThat(result).isEqualTo(1L);

        //verfiy
        Mockito.verify(draftPostRepository).deleteByDraftPostIdAndMember_MemberId(1L, 1L);
    }

    @Test
    @DisplayName("임시글 생성")
    void createDraftPost() {
        // 임시글 생성 파람
        CreateDraftPostDTO createDraftPostDTO = draftPostInitData.getCreateDraftPostDTO();
        DraftPost createDraftPost = createDraftPostDTO.toDraftPostEntity(Member.builder().memberId(1L).build());

        //임시글 생성 리턴값
        DraftPost savedDraftPost = draftPostInitData.getDraftPost();

        //given
        BDDMockito.given(draftPostRepository.save(Mockito.any(DraftPost.class)))
                .willAnswer(invocation -> {
                    return invocation.getArgument(0);
                });

        BDDMockito.given(draftTagRepository.save(Mockito.any(DraftTag.class)))
                .willAnswer(invocation -> {
                    return invocation.getArgument(0); // 들어온 DraftTag를 다시 보내기
                });

        //when
        Optional<SearchDraftPostDTO> result = draftPostService.createDraftPost(createDraftPostDTO, 1L);

        // expect
        List<String> savedDraftTagList = createDraftPostDTO.getTagList();
        SearchDraftPostDTO expectResult = SearchDraftPostDTO.toDTO(savedDraftPost, savedDraftTagList);

//        log.info("expectResult = {}", expectResult.toString());
//        log.info("result = {}", result.toString());
        Assertions.assertThat(expectResult.getMemberId()).isEqualTo(result.get().getMemberId());
        Assertions.assertThat(expectResult.getTitle()).isEqualTo(result.get().getTitle());
        Assertions.assertThat(expectResult.getContent()).isEqualTo(result.get().getContent());
        Assertions.assertThat(expectResult.getTagList()).isEqualTo(result.get().getTagList());

        //verify
        Mockito.verify(draftPostRepository).save(Mockito.any(DraftPost.class));
        Mockito.verify(draftTagRepository, Mockito.times(savedDraftTagList.size())).save(Mockito.any(DraftTag.class));

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