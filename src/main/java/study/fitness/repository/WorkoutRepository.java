package study.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.fitness.domain.Workout;

import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    Optional<Workout> findByName(String name);

    Optional<Workout> findById(Long id);

    boolean existsByNameAndUserName(String name, String userName);
}
