package study.fitness.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    @NotBlank(message = "id는 필수항목입니다.")
    private String userId;
    @NotBlank(message = "패스워드는 필수항목입니다.")
    private String password;
    @NotBlank(message = "닉네임은 필수항목입니다.")
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
