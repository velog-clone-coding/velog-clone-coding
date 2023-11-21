package velog.sideProject.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import velog.sideProject.entity.Member;
import velog.sideProject.global.entity.Authority;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String email;
    private String name;
    private String title;
    public Member toMember() {
        return Member.builder()
                .memberEmail(email)
                .memberName(name)
                .velogTitle(title)
                .authority(Authority.ROLE_USER)
                .build();
    }
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, null);
    }
}