package study.fitness.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private WorkoutType type;

    private String description;

    private String userName;

    @OneToMany(mappedBy = "workout")
    private List<Routine> routine;

    @Builder
    private Workout(String name, WorkoutType type, String description, String userName) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.userName = userName;
    }

    static public Workout of(String name, WorkoutType type, String description, String userName) {
        return Workout.builder()
                .name(name)
                .type(type)
                .description(description)
                .userName(userName)
                .build();
    }

    public void update(String name, WorkoutType type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }
}
