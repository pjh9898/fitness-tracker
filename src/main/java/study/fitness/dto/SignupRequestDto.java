package study.fitness.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    private String userId;

    private String password;

    private String nickname;

    @Builder
    private SignupRequestDto(String userId, String password, String nickname) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
    }

    static public SignupRequestDto of(String userId, String password, String nickname) {
        return SignupRequestDto.builder()
                .userId(userId)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
