package study.fitness.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {

    @Null
    private String userId;

    private String password;

    private String nickname;
}
