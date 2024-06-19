package study.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.fitness.domain.Routine;
import study.fitness.domain.Workout;

import java.util.Optional;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    Optional<Routine> findById(Long id);

    boolean existsByNameAndUserId(String name, Long userId);
}
