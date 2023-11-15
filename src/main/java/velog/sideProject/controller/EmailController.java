package velog.sideProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import velog.sideProject.service.EmailService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;
    @PostMapping("/mailConfirm")
    @ResponseBody
    String mailConfirm(@RequestParam("email") String email) throws Exception {

        String code = emailService.sendSimpleMessage(email);
        System.out.println("인증코드 : " + code);
        return code;
    }
}
