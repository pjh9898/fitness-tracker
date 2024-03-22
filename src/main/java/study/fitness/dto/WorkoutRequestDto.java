package study.fitness.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import study.fitness.domain.Workout;
import study.fitness.domain.WorkoutType;

@Getter
public class WorkoutRequestDto {

    @NotBlank(message = "이름은 필수항목입니다.")
    private String name;
    @NotBlank(message = "운동 타입은 필수항목입니다.")
    private WorkoutType type;
    private String description;

    @Builder
    private WorkoutRequestDto(String name, WorkoutType type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public Workout toEntity(String userName) {
        return Workout.builder()
                .name(name)
                .type(type)
                .description(description)
                .userName(userName)
                .build();
    }

    static public WorkoutRequestDto of(String name, WorkoutType type, String description) {
        return WorkoutRequestDto.builder()
                .name(name)
                .type(type)
                .description(description)
                .build();
    }
}
