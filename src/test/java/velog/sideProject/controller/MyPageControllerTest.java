package velog.sideProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@Slf4j
class MyPageControllerTest {

    @Autowired MyPageController myPageController;

    // TODO: 테스트 기능 추가 필요
    @Test
    @DisplayName("임시게시글 조회")
    void draftPosts() {
        throw new RuntimeException();
    }
}