package study.fitness.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JWTInputDto {
    private String userId;
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
