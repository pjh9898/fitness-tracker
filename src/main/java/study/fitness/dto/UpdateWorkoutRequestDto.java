package study.fitness.dto;

import lombok.Builder;
import lombok.Getter;
import study.fitness.domain.WorkoutType;

@Getter
public class UpdateWorkoutRequestDto {

    private String name;
    private WorkoutType type;
    private String desc;

    @Builder
    private UpdateWorkoutRequestDto(String name, WorkoutType type, String desc) {
        this.name = name;
        this.type = type;
        this.desc = desc;
    }

    static public UpdateWorkoutRequestDto of(String name, WorkoutType type, String desc) {
        return UpdateWorkoutRequestDto.builder()
                .name(name)
                .type(type)
                .desc(desc)
                .build();
    }
}
