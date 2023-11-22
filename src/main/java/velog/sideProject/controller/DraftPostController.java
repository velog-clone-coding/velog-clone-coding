package velog.sideProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import velog.sideProject.controller.dto.CreateDraftPostDTO;
import velog.sideProject.controller.dto.SearchDraftPostDTO;
import velog.sideProject.exception.exception.VelogNotFoundException;
import velog.sideProject.service.DraftPostService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/draftPost")
public class DraftPostController {

    private final DraftPostService draftPostService;

    /**
     * TODO: jwt에서 멤버 정보 가져오는걸로 변경 필요
     **/
    Long memberId = 1L;

    /**
     * select DraftPostList with member_id
     **/
    @GetMapping("/saves")
    public List<SearchDraftPostDTO> getDraftPostList() {
        log.info("getDraftPostList request");

        return draftPostService.getDraftPostListWithId(memberId);

    }

    /**
     * select DraftPost with postId
     **/
    @GetMapping("/write")
    public SearchDraftPostDTO getDraftPost(@RequestParam Long postId) {
        log.info("getDraftPost request");
        Optional<SearchDraftPostDTO> draftPostDTO = draftPostService.getDraftPostWithPostIdMemberId(postId, memberId);

        return draftPostDTO.orElseThrow(() -> new VelogNotFoundException("DraftPost not found with postID: " + postId + ", memberId: " + memberId));

    }

    /**
     * delete DraftPost with member_id & post_id
     **/
    @DeleteMapping("/write")
    public void deleteDraftPost(@RequestParam Long postId) {
        log.info("deleteDraftPost request");

        if (1 != draftPostService.deleteDraftPostWithPostIdMemberId(postId, memberId)) {
            throw new VelogNotFoundException("DraftPost not found with postID: " + postId + ", memberId: " + memberId);
        }
    }

    /** insert DraftPost with member_id **/
    @PostMapping("/write")
    public SearchDraftPostDTO createDraftPost(@RequestBody CreateDraftPostDTO createDraftPostDTO) {
        log.info("createDraftPost request");

        Optional<SearchDraftPostDTO> draftPost = draftPostService.createDraftPost(createDraftPostDTO, memberId);

        return draftPost.orElseThrow(() -> new RuntimeException("서버 에러"));
    }
}