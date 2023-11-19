package velog.sideProject.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@Slf4j
class MyPageServiceTest {

    @Autowired MyPageService myPageService;

    // TODO: 테스트 기능 추가 필요
    @Test
    @DisplayName("멤버아이디로 임시게시글 조회")
    void getDraftPostWithId() {
        throw new RuntimeException();
    }
}