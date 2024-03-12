package study.fitness.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotBlank(message = "id는 필수항목입니다.")
    private String userId;
    @NotBlank(message = "패스워드는 필수항목입니다.")
    private String password;

    @Builder
    private LoginRequestDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    static public LoginRequestDto of(String userId, String password) {
        return LoginRequestDto.builder()
                .userId(userId)
                .password(password)
                .build();
    }
}
