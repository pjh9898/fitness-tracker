package study.fitness.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.fitness.domain.User;
import study.fitness.dto.LoginRequestDto;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class UserApiServiceTest {

    @Autowired
    UserService userService;

    @Test
    void 회원가입() {
        User user = User.of("haribo321", "pwd123", "userNick");

        String id = userService.signUp(user);
        User findUser = userService.fineOne(id).orElseThrow(() -> new RuntimeException("User not found"));

        assertThat(user).isEqualTo(findUser);
    }

    @Test
    void 로그인() {
        LoginRequestDto info = LoginRequestDto.of("testId1", "testpwd1");
        System.out.println("info.getUserId() = " + info.getUserId());

        String token = userService.login(info);

        assertThat(token).isNotNull();
    }
}