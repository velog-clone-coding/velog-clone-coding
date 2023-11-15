package velog.sideProject.repository.draftpost.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.drfatpost.DraftPost_DraftTag;
import velog.sideProject.entity.drfatpost.idclass.DraftPost_DraftTagPK;

    public interface DraftPost_DraftTagRepository extends JpaRepository<DraftPost_DraftTag, DraftPost_DraftTagPK> {
}
