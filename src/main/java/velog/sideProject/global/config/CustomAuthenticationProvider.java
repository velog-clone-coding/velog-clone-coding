package velog.sideProject.global.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import velog.sideProject.repository.member.MemberRepository;

import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final MemberRepository memberRepository;

    @Autowired
    public CustomAuthenticationProvider(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName(); // 사용자 이름 가져오기

        // 사용자 이름으로 사용자를 찾거나 다른 인증 로직을 수행하고 인증 성공 시 Authentication 객체 반환
        if (usernameIsValid(username)) {
            // 사용자가 유효한 경우, 이 사용자를 나타내는 UserDetails 객체를 생성하여 반환
            // 예시에서는 UserDetails를 구현하는 CustomUserDetails를 반환하도록 하였습니다.
            return new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        } else {
            // 사용자가 유효하지 않은 경우 null을 반환하거나 예외를 던져서 인증 실패를 나타냅니다.
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // UsernamePasswordAuthenticationToken에 대한 인증을 처리할 수 있는지 여부를 반환
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    // 사용자가 유효한지 여부를 확인하는 메서드 (예시)
    private boolean usernameIsValid(String username) {
        // 실제 사용자 데이터베이스 조회 또는 사용자 이름을 검증하는 로직을 구현합니다.
        if (!memberRepository.existsByMemberEmail(username)) {
            throw new RuntimeException("User with email " + username + " not found");
        }
        // 예시로, 사용자 이름이 특정 조건을 만족하는지 여부를 확인하여 유효성을 판단합니다.
        return username != null && !username.isEmpty(); // 예시로 단순히 null 또는 빈 문자열이 아니면 유효하다고 가정합니다.
    }
}