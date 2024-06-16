package study.fitness.api;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import study.fitness.domain.User;
import study.fitness.dto.LoginRequestDto;
import study.fitness.dto.LoginResponseDto;
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
    public ResponseEntity<Map<String, Boolean>> checkIdDuplicate(@PathVariable("userId") String userId) {
        boolean isAvailable = userService.validateDuplicateUser(userId);
        return ResponseEntity.ok().body(Collections.singletonMap("isAvailable", isAvailable));
    }

    @PostMapping("/signup")
    public CreateSignupResponse saveUser(@RequestBody @Valid SignupRequestDto requestDto) {
        String id = requestDto.getUserId();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();

        User user = User.of(id, password, nickname);

        String userId = userService.signUp(user);
        return new CreateSignupResponse(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> getUserProfile(@RequestBody @Valid LoginRequestDto request) {
        LoginResponseDto loginResponseDto = this.userService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
    }

    @Data
    public static class CreateSignupResponse {
        private String userId;

        public CreateSignupResponse(String userId) {
            this.userId = userId;
        }
    }


}
