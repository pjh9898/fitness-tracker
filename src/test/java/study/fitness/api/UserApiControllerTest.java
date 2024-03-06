package study.fitness.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import study.fitness.service.UserService;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class UserApiControllerTest {

    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 아이디_중복체크API() throws Exception {
        mockMvc.perform(get("/id/exists/testId1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isAvailable").value(true));
    }

    @Test
    void 아이디_중복체크API_예외() throws Exception {
        mockMvc.perform(get("/id/exists/testId1234598")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    Exception resolvedException = result.getResolvedException();
                    if (resolvedException == null) {
                        throw new AssertionError("예외가 발생하지 않았습니다.");
                    }
                    if (!resolvedException.getMessage().equals("이미 존재하는 회원입니다.")) {
                        throw new AssertionError("예외 메시지가 일치하지 않습니다.");
                    }
                });
    }

    @Test
    void 회원가입API() throws Exception {
        Map<String, String> input = new HashMap<>();
        input.put("userId", "testId1231");
        input.put("password", "testpwd1231");
        input.put("nickname", "haribo1231");

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andDo(print());

    }
}