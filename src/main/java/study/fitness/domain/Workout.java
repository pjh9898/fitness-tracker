package study.fitness.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private WorkoutType type;
    private String desc;
    private String userName;
    @OneToMany(mappedBy = "workout")
    private List<Routine> routine;

    @Builder
    private Workout(String name, WorkoutType type, String desc) {
        this.name = name;
        this.type = type;
        this.desc = desc;
    }

    static public Workout of(String name, WorkoutType type, String desc) {
        return Workout.builder()
                .name(name)
                .type(type)
                .desc(desc)
                .build();
    }
}
