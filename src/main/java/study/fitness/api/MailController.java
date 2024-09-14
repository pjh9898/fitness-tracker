package study.fitness.api;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import study.fitness.dto.MailDto;
import study.fitness.service.MailService;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @ResponseBody
    @PostMapping("/emailCheck")
    public String emailCheck(@RequestBody MailDto mailDto) throws MessagingException {
        System.out.println("!!!!!!!!!");
        return mailService.sendSimpleMessage(mailDto.getEmail());
    }
}
