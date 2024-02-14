package study.fitness.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.fitness.domain.User;
import study.fitness.dto.LoginRequestDto;
import study.fitness.dto.SignupRequestDto;
import study.fitness.service.UserService;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/id/exists/{userId}")
    public ResponseEntity<Map<String, Boolean>> checkIdDuplicate(@PathVariable String userId) {
        boolean isAvailable = userService.validateDuplicateUser(userId);
        return ResponseEntity.ok().body(Collections.singletonMap("isAvailable", isAvailable));
    }

    @PostMapping("/signup")
    public CreateSignupResponse saveUser(@RequestBody @Validated SignupRequestDto requestDto) {
        User user = new User();
        user.setUserId(requestDto.getUserId());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        System.out.println("user.getPassword() = " + user.getPassword());
        user.setNickname(requestDto.getNickname());

        String userId = userService.signUp(user);
        return new CreateSignupResponse(userId);
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
    private static class CreateSignupResponse {
        private String userId;

        public CreateSignupResponse(String userId) {
            this.userId = userId;
        }
    }


}
