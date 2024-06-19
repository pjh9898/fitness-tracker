package study.fitness.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.fitness.domain.Workout;
import study.fitness.domain.WorkoutType;

@Getter
@NoArgsConstructor
public class WorkoutPostRequestDto {

    @NotBlank(message = "이름은 필수항목입니다.")
    private String name;
    @NotNull(message = "운동 타입은 필수항목입니다.")
    private WorkoutType type;
    private String description;

    @Builder
    private WorkoutPostRequestDto(String name, WorkoutType type, String description) {
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

    static public WorkoutPostRequestDto of(String name, WorkoutType type, String description) {
        return WorkoutPostRequestDto.builder()
                .name(name)
                .type(type)
                .description(description)
                .build();
    }
}
