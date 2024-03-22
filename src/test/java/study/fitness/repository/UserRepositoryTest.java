package study.fitness.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import study.fitness.domain.User;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void 유저찾기_유저아이디로() {
        //given
        User user = User.of("testId1", "testpwd1", "haribo1");
        userRepository.save(user);

        //when
        Optional<User> result = userRepository.findByUserId("testId1");

        //then
        Assertions.assertThat(result.isPresent()).isTrue();
        Assertions.assertThat(user.getUserId()).isEqualTo(result.get().getUserId());
    }

    @Test
    void 유저찾기_닉네임으로() {
        //given
        User user = User.of("testId1", "testpwd1", "haribo1");
        userRepository.save(user);

        //when
        Optional<User> result = userRepository.findByNickname(user.getNickname());

        //then
        Assertions.assertThat(result.isPresent()).isTrue();
        Assertions.assertThat(result.get().getNickname()).isEqualTo(user.getNickname());
    }

    @Test
    void 존재하는_아이디인지_체크() {
        //given
        User user = User.of("testId1", "testpwd1", "haribo1");
        userRepository.save(user);

        //when
        boolean result = userRepository.existsByUserId(user.getUserId());

        //then
        Assertions.assertThat(result).isTrue();
    }
}