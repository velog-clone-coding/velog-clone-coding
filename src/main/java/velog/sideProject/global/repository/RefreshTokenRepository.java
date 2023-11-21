package velog.sideProject.global.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.global.entity.RefreshToken;

import java.util.Optional;
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByKey(String key);
}
