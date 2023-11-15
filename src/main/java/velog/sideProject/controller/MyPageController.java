package velog.sideProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import velog.sideProject.controller.dto.DraftPostDTO;
import velog.sideProject.controller.dto.DraftPostWithTagDTO;
import velog.sideProject.service.MyPageService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MyPageController {

    private final MyPageService myPageService;


    /** select DraftPost with member_id**/
    @GetMapping("/saves")
    public List<DraftPostWithTagDTO> DraftPosts(){
        log.info("saves request");

        /** TODO: jwt에서 멤버 정보 가져오는걸로 변경 필요 **/
        Long member_id = 1L;

        return myPageService.getDraftPostWithId(member_id);

    };

}
