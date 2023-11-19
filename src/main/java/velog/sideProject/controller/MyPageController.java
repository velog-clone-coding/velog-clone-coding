package velog.sideProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import velog.sideProject.controller.dto.SearchDraftPostDTO;
import velog.sideProject.exception.exception.VelogNotFoundException;
import velog.sideProject.service.MyPageService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/myPage")
public class MyPageController {

    private final MyPageService myPageService;

    /** TODO: jwt에서 멤버 정보 가져오는걸로 변경 필요 **/
    Long memberId = 1L;

    /** select DraftPostList with member_id **/
    @GetMapping("/saves")
    public List<SearchDraftPostDTO> getDraftPostList() {
        log.info("getDraftPostList request");

        return myPageService.getDraftPostListWithId(memberId);

    }

    /** select DraftPost with postId **/
    @GetMapping("/write")
    public SearchDraftPostDTO getDraftPost(@RequestParam Long postId){
        log.info("getDraftPost request");
        Optional<SearchDraftPostDTO> draftPostDTO = myPageService.getDraftPostWithPostIdMemberId(postId, memberId);

        return draftPostDTO.orElseThrow(() -> new VelogNotFoundException("DraftPost not found with postID: " + postId + ", memberId: " + memberId));

    }

}
