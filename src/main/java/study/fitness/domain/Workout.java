package study.fitness.domain;

import jakarta.persistence.*;
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
    private String userName;
    @OneToMany(mappedBy = "workout")
    private List<Routine> routine;

    public Workout(String name, WorkoutType type) {
        this.name = name;
        this.type = type;
    }
}
