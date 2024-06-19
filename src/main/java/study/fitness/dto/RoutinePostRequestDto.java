package study.fitness.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.fitness.domain.Routine;

import java.util.List;

@Getter
@NoArgsConstructor
public class RoutinePostRequestDto {
    String name;
    List<WorkoutListDto> body;

    @Builder
    private RoutinePostRequestDto(String name, List<WorkoutListDto> body) {
        this.name = name;
        this.body = body;
    }

    public Routine toEntity(Long userId) {
        ObjectMapper objectMapper = new ObjectMapper();
        String parsedBody = "";
        try {
            parsedBody = objectMapper.writeValueAsString(body);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Routine.builder()
                .name(name)
                .body(parsedBody)
                .userId(userId)
                .build();
    }

    static public RoutinePostRequestDto of(String name, List<WorkoutListDto> body) {
        return RoutinePostRequestDto.builder()
                .name(name)
                .body(body)
                .build();
    }
}
