package study.fitness.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.fitness.domain.Workout;
import study.fitness.repository.WorkoutRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public List<Workout> findWorkouts() {
        return workoutRepository.findAll();
    }

    public void createWorkout(Workout workout, String userName) {
        validateDuplicateWorkout(workout.getName(), userName);

        workoutRepository.save(workout);
    }

    private boolean validateDuplicateWorkout(String name, String username) {
        boolean isDuplicate = workoutRepository.existsByNameAndUserName(name, username);

        if (isDuplicate) {
            throw new IllegalStateException("이미 존재하는 운동입니다.");
        }

        return true;
    }

    public void updateWorkout(Workout workout, String userName) {
        boolean isExist = validateNotExistWorkout(workout.getName(), userName);

        if (isExist) {
            workoutRepository.save(workout);
        }
    }

    public void deleteWorkout(Long workoutId) {
        boolean isExist = validateNotExistWorkout(workout.getName(), userName);

        if (isExist) {
            workoutRepository.deleteById(workoutId);
        }
    }

    private boolean validateNotExistWorkout(String name, String username) {
        boolean isExist = workoutRepository.existsByNameAndUserName(name, username);

        if (!isExist) {
            throw new IllegalStateException("존재하지 않는 운동입니다.");
        }

        return true;
    }
}
