package study.fitness.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    private String userId;
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
