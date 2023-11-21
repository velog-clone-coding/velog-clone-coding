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
import velog.sideProject.config.initData.DraftPostInitData;
import velog.sideProject.controller.dto.CreateDraftPostDTO;
import velog.sideProject.controller.dto.SearchDraftPostDTO;
import velog.sideProject.entity.Member;
import velog.sideProject.entity.drfatPost.DraftPost;
import velog.sideProject.entity.drfatPost.DraftTag;
import velog.sideProject.repository.draftpost.jpa.DraftPostRepository;
import velog.sideProject.repository.draftpost.jpa.DraftTagRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest(classes = {WritePageService.class, DraftPostRepository.class, DraftTagRepository.class})
class WritePageServiceTest {

    @Autowired
    WritePageService writePageService;

    @MockBean
    DraftPostRepository draftPostRepository;

    @MockBean
    DraftTagRepository draftTagRepository;

    DraftPostInitData draftPostInitData = new DraftPostInitData();

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
        Optional<SearchDraftPostDTO> result = writePageService.createDraftPost(createDraftPostDTO, 1L);

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
}