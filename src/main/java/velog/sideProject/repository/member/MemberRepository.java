package velog.sideProject.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
