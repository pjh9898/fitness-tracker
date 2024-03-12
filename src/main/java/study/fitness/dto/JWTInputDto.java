package study.fitness.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JWTInputDto {
    @NotBlank(message = "id는 필수항목입니다.")
    private String userId;
    @NotBlank(message = "닉네임은 필수항목입니다.")
    private String nickname;

    @Builder
    private JWTInputDto(String userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }

    static public JWTInputDto of(String userId, String nickname) {
        return JWTInputDto.builder()
                .userId(userId)
                .nickname(nickname)
                .build();
    }
}
