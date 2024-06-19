package study.fitness.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private String body;
    @Enumerated(EnumType.STRING)
    private RoutineStatus status;

    private Long userId;


    @Builder
    private Routine(String name, String body, Long userId) {
        this.name = name;
        this.body = body;
        this.status = RoutineStatus.NOTDONE;
        this.userId = userId;
    }

    static public Routine of(String body, Long userId) {
        return Routine.builder()
                .body(body)
                .userId(userId)
                .build();
    }
}
