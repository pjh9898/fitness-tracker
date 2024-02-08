package study.fitness.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    
    private String userId;

    private String password;

    private String nickname;
}
