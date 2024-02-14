package study.fitness.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.fitness.domain.User;
import study.fitness.dto.LoginRequestDto;
import study.fitness.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class UserApiServiceTest {

    @Autowired
    UserService userService;

    @Test
    void 회원가입() {
        User user = new User();
        user.setUserId("haribo321");
        user.setPassword("pwd123");
        user.setNickname("userNick");

        String id = userService.signUp(user);
        User findUser = userService.fineOne(id).orElseThrow(() -> new RuntimeException("User not found"));

        assertThat(user).isEqualTo(findUser);
    }

    @Test
    void 로그인() {
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setUserId("testId123");
        loginRequestDto.setPassword("testpwd123");

        String token = userService.login(loginRequestDto);

        assertThat(token).isNotNull();
    }
}