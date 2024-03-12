package study.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.fitness.domain.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    boolean existsByNameAndUserName(String name, String userName);
}
