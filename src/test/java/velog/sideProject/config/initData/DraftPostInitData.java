package velog.sideProject.config.initData;

import lombok.Getter;
import velog.sideProject.controller.dto.CreateDraftPostDTO;
import velog.sideProject.controller.dto.SearchDraftPostDTO;
import velog.sideProject.entity.Member;
import velog.sideProject.entity.drfatpost.DraftPost;
import velog.sideProject.entity.drfatpost.DraftTag;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * init 부분에서만 사용
 */
@Getter
public class DraftPostInitData {

    public DraftPostInitData() {
        initSearchDraftPostList();
        initSearchDraftPostDTOList();

    }

    private List<DraftPost> draftPostList = new ArrayList<>();
    private List<SearchDraftPostDTO> searchDraftPostDTOList = new ArrayList<>();
    private List<DraftTag> draftTagList = new ArrayList<>();

    private CreateDraftPostDTO createDraftPostDTO = CreateDraftPostDTO.builder()
            .content("content")
            .title("title")
            .tagList(List.of("tag1", "tag2"))
            .build();

    private DraftPost draftPost = DraftPost.builder().
                draftPostId(1L)
                .draftPostTitle("title")
                .draftPostContent("content")
                .draftPostModifiedAt(LocalDateTime.now())
                .draftTagList(List.of(new DraftTag("tag1", DraftPost.builder().draftPostId(1L).build())))
                .member(Member.builder().memberId(1L).build())
            .build();


    private SearchDraftPostDTO searchDraftPostDTO = SearchDraftPostDTO.builder()
            .postId(1L)
            .memberId(1L)
            .title("title")
            .content("content")
            .agoDate(LocalDateTime.now())
            .tagList(List.of("tag1", "tag2")).build();

    private void initSearchDraftPostList() {
        draftPostList.add(DraftPost.builder()
                .draftPostId(1L)
                .draftPostTitle("title1")
                .draftPostContent("content1")
                .draftPostModifiedAt(LocalDateTime.now())
                .draftTagList(List.of(new DraftTag("tag1", DraftPost.builder().draftPostId(1L).build())))
                .member(Member.builder().memberId(1L).build())
                        .build());

        draftPostList.add(DraftPost.builder()
                .draftPostId(2L)
                .draftPostTitle("title2")
                .draftPostContent("content2")
                .draftPostModifiedAt(LocalDateTime.now())
                .member(Member.builder().memberId(1L).build())
                .draftTagList(List.of())
                .build());

    }

    private void initSearchDraftPostDTOList() {
        searchDraftPostDTOList.add(SearchDraftPostDTO.builder()
                .postId(1L)
                .memberId(1L)
                .title("title1")
                .content("content1")
                .agoDate(LocalDateTime.now())
                .tagList(List.of("tag1", "tag2")).build());
        searchDraftPostDTOList.add(SearchDraftPostDTO.builder()
                .postId(2L)
                .memberId(1L)
                .title("title2")
                .content("content2")
                .agoDate(LocalDateTime.now())
                .tagList(null).build());
    }


}


