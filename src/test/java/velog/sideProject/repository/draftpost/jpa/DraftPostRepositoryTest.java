package velog.sideProject.repository.draftpost.jpa;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import velog.sideProject.entity.drfatpost.DraftPost;
import velog.sideProject.entity.drfatpost.DraftTag;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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