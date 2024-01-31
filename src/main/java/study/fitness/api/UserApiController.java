package study.fitness.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.fitness.domain.Routine;
import study.fitness.domain.User;
import study.fitness.dto.LoginRequestDto;
import study.fitness.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @GetMapping("/user-list")
    public Result getUserList() {
        List<User> findUsers = userService.findUsers();
        List<UserDto> collect = findUsers.stream()
                .map(m -> new UserDto(m.getNickname(), m.getRoutines()))
                .toList();

        return new Result(collect);
    }

    @PostMapping("/signup")
    public CreateSignupResponse saveUser(@RequestBody @Validated User user) {
        Long id = userService.join(user);
        return new CreateSignupResponse(id);
    }

    @PostMapping("/login")
    public ResponseEntity<String> getUserProfile(@Validated @RequestBody LoginRequestDto request) {
        String token = this.userService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class UserDto {
        private String nickname;
        private List<Routine> routines;
    }

    @Data
    private static class CreateSignupResponse {
        private Long id;

        public CreateSignupResponse(Long id) {
            this.id = id;
        }
    }


}
