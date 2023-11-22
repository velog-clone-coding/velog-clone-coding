package velog.sideProject.repository.draftpost.jpa;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import velog.sideProject.entity.drfatPost.DraftPost;
import velog.sideProject.entity.drfatPost.DraftTag;

@Transactional
@SpringBootTest(classes = {DraftPostRepository.class})
@Slf4j
class DraftPostRepositoryTest {



    // TODO: 테스트 기능 추가 필요
    @Test
    @DisplayName("멤버아이디로 임시게시글 조회")
    void findByMember_MemberId() {
        throw new RuntimeException();
    }

    @Test
    @DisplayName("포스트아이디로 태그 조회")
    void findTagStringsByPostId() {
        throw new RuntimeException();
    }
}