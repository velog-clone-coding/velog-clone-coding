package velog.sideProject.repository.member;

import org.springframework.data.jpa.repository.Query;
import velog.sideProject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByMemberEmail(String email);

    boolean existsByMemberEmail(String email);

    @Query("select m.memberId from Member m where m.memberEmail = :email")
    Long findIdByMemberEmail(String email);


    Optional<Member> findByMemberId(Long memberId);

}