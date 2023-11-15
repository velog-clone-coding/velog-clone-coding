package velog.sideProject.repository.draftpost.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
