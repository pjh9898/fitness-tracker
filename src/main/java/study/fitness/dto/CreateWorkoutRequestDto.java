package study.fitness.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import study.fitness.domain.WorkoutType;

@Getter
public class CreateWorkoutRequestDto {

    @NotBlank(message = "이름은 필수항목입니다.")
    private String name;
    @NotBlank(message = "운동 타입은 필수항목입니다.")
    private WorkoutType type;
    private String desc;

    @Builder
    private CreateWorkoutRequestDto(String name, WorkoutType type, String desc) {
        this.name = name;
        this.type = type;
        this.desc = desc;
    }

    static public CreateWorkoutRequestDto of(String name, WorkoutType type, String desc) {
        return CreateWorkoutRequestDto.builder()
                .name(name)
                .type(type)
                .desc(desc)
                .build();
    }
}
