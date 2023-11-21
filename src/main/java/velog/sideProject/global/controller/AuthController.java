package velog.sideProject.global.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import velog.sideProject.global.dto.MemberRequestDto;
import velog.sideProject.global.dto.MemberResponseDto;
import velog.sideProject.global.dto.TokenDto;
import velog.sideProject.global.dto.TokenRequestDto;
import velog.sideProject.global.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(
            @RequestBody MemberRequestDto memberRequestDto) throws Exception {
        return ResponseEntity.ok(authService.login(memberRequestDto.getEmail(), memberRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}