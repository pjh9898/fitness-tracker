package study.fitness.dto;

import lombok.Builder;
import lombok.Getter;
import study.fitness.domain.Workout;
import study.fitness.domain.WorkoutType;

@Getter
public class WorkoutPatchRequestDto {

    private String name;
    private WorkoutType type;
    private String description;

    @Builder
    private WorkoutPatchRequestDto(String name, WorkoutType type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public WorkoutPatchRequestDto() {
    }

    public Workout toEntity(String userName) {
        return Workout.builder()
                .name(name)
                .type(type)
                .description(description)
                .userName(userName)
                .build();
    }

    static public WorkoutPatchRequestDto of(String name, WorkoutType type, String description) {
        return WorkoutPatchRequestDto.builder()
                .name(name)
                .type(type)
                .description(description)
                .build();
    }
}