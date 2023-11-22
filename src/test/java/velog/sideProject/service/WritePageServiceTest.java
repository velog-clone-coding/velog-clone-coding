package velog.sideProject.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import velog.sideProject.common.initData.DraftPostInitData;
import velog.sideProject.controller.dto.CreateDraftPostDTO;
import velog.sideProject.controller.dto.CreatePostDTO;
import velog.sideProject.controller.dto.SearchDraftPostDTO;
import velog.sideProject.entity.File;
import velog.sideProject.entity.Member;
import velog.sideProject.entity.drfatPost.DraftPost;
import velog.sideProject.entity.drfatPost.DraftTag;
import velog.sideProject.entity.post.Post;
import velog.sideProject.entity.post.Tag;
import velog.sideProject.entity.series.Series;
import velog.sideProject.repository.FileRepository;
import velog.sideProject.repository.draftpost.jpa.DraftPostRepository;
import velog.sideProject.repository.draftpost.jpa.DraftTagRepository;
import velog.sideProject.repository.member.MemberRepository;
import velog.sideProject.repository.post.jpa.PostRepository;
import velog.sideProject.repository.post.jpa.Post_TagRepository;
import velog.sideProject.repository.post.jpa.TagRepository;
import velog.sideProject.repository.series.jpa.SeriesRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
@ComponentScan(basePackages = "velog.sideProject.repository")
class WritePageServiceTest {

    @Autowired
    WritePageService writePageService;
    @MockBean
    DraftPostRepository draftPostRepository;
    @MockBean
    private DraftTagRepository draftTagRepository;
    @MockBean
    private PostRepository postRepository;
    @MockBean
    private TagRepository tagRepository;
    @MockBean
    private Post_TagRepository postTagRepository;
    @MockBean
    private MemberRepository memberRepository;
    @MockBean
    private FileRepository fileRepository;
    @MockBean
    private SeriesRepository seriesRepository;


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

    /** TODO: 테스트 코드 작성 필요 **/
    @Test
    @Disabled
    @DisplayName("게시글 생성")
    void createPost() {
        Long memberId = 1L;
        CreatePostDTO createPostDTO = draftPostInitData.getCreatePostDTO();

        // 멤버 조회
        Optional<Member> returnMember = Optional.ofNullable(Member.builder().memberId(1L).build());
        BDDMockito.given(memberRepository.findByMemberId(memberId))
                .willReturn(returnMember);
        // 썸네일 조회
        Optional<File> returnFile = Optional.ofNullable(File.builder().fileId(2L).build());
        BDDMockito.given(fileRepository.findByFileId(createPostDTO.getThumbnailId()))
                .willReturn(returnFile);
        // 시리즈 조회
        Optional<Series> returnSeries = Optional.ofNullable(Series.builder().seriesId(3L).build());
        BDDMockito.given(seriesRepository.findBySeriesId(createPostDTO.getSeriesId()))
                .willReturn(returnSeries);

        // 태그, 게시글 정보 분리
        Post post = createPostDTO.toPostEntity(returnMember.get(), returnFile.orElse(null), returnSeries.orElse(null));
        List<Tag> tagList = createPostDTO.toTagEntity();

        // 게시글 생성
        Post returnPost = Post.builder().postId(post.getPostId()).build();
        BDDMockito.given(postRepository.save(post))
                .willReturn(returnPost);


    }
}