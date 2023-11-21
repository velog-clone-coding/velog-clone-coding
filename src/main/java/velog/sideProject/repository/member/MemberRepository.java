package velog.sideProject.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import velog.sideProject.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(Long memberId);
}
