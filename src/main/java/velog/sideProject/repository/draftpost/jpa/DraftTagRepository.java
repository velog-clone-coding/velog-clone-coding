package velog.sideProject.repository.draftpost.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.drfatpost.DraftTag;

public interface DraftTagRepository extends JpaRepository<DraftTag, Long> {
}
