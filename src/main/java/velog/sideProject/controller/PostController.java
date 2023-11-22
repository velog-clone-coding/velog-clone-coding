package velog.sideProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import velog.sideProject.controller.dto.CreatePostDTO;
import velog.sideProject.controller.dto.SearchPostDTO;
import velog.sideProject.service.PostService;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    /**
     * TODO: jwt에서 멤버 정보 가져오는걸로 변경 필요
     **/
    Long memberId = 1L;



    /** insert Post with member_id **/
    @PostMapping("/write")
    public SearchPostDTO createPost(@RequestBody CreatePostDTO createPostDTO) {
        log.info("createPost request");

        Optional<SearchPostDTO> searchPostDTO = postService.createPost(createPostDTO, memberId);

        return searchPostDTO.orElseThrow(() -> new RuntimeException("서버 에러"));
    }
}

