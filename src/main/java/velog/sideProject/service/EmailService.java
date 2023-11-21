package velog.sideProject.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.username}")
    private String AdminEmail;

    @Autowired
    private  JavaMailSender emailsender;

    public MimeMessage loginMessage(String to) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = emailsender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject("Velog 회원가입");

        String msgBody = "<div style='margin: 100px;'>";
        msgBody += "<h1>안녕하세요!</h1>";
        msgBody += "<p>회원 가입을 계속하시려면 다음 링크를 클릭하세요:</p>";
        msgBody += "<a href='http://localhost:8080/auth/signup" + "' style='display: inline-block; padding: 10px 20px; background-color: #007bff; color: #fff; text-decoration: none; border-radius: 5px;'>회원하기</a>";
        msgBody += "<p>이 링크는 일회성이며, 24시간 동안만 유효합니다.</p>";
        msgBody += "</div>";

        helper.setText(msgBody, true);
        helper.setFrom(new InternetAddress(AdminEmail, "TEST_ADMIN"));

        return message;
    }
    /**
     * 메일 발송
     * sendSimpleMessage 의 매개변수로 들어온 to 는 곧 이메일 주소가 되고,
     * MimeMessage 객체 안에 내가 전송할 메일의 내용을 담는다.
     * 그리고 bean 으로 등록해둔 javaMail 객체를 사용해서 이메일 send!!
     */
    public String sendTokenLink(String to) throws Exception {

        // TODO Auto-generated method stub
        MimeMessage message = loginMessage(to); // 메일 발송
        try {// 예외처리
            emailsender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }

        return "success";
    }
}
