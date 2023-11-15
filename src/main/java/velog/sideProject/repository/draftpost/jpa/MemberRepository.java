package velog.sideProject.repository.draftpost.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
