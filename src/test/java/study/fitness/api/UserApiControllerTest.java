package study.fitness.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import study.fitness.domain.User;
import study.fitness.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserApiController.class)
class UserApiControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    @DisplayName("회원가입 테스트")
    void saveUserTest() throws Exception {
        User user = new User();
        user.setId(10L);
        user.setUserId("testId1234");
        user.setPassword("testpwd12345");
        user.setNickname("haribo123");

        mvc.perform(post("/signup")
                        .content(String.valueOf(user)))
                .andExpect(status().isCreated());
    }

}