package study.fitness.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.fitness.domain.Workout;
import study.fitness.dto.WorkoutRequestDto;
import study.fitness.repository.WorkoutRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public List<Workout> findWorkouts() {
        return workoutRepository.findAll();
    }

    public Long createWorkout(WorkoutRequestDto requestDto, String userName) {
        Workout workout = requestDto.toEntity(userName);
        validateExistWorkoutByNameAndUserName(workout.getName(), workout.getUserName());

        workoutRepository.save(workout);

        return workout.getId();
    }


    public CreateUpdateWorkoutResponse updateWorkout(WorkoutRequestDto requestDto, String userName) {
        Workout workout = requestDto.toEntity(userName);
        validateNotExistWorkoutByNameAndUserName(workout.getName(), workout.getUserName());

        workout.update(requestDto.getName(), requestDto.getType(), requestDto.getDescription());

        return new CreateUpdateWorkoutResponse(workout);
    }

    public Long deleteWorkout(Long id) {
        validateNotExistWorkoutById(id);

        workoutRepository.deleteById(id);

        return id;
    }

    private boolean validateExistWorkoutByNameAndUserName(String name, String userName) {
        boolean isDuplicate = workoutRepository.existsByNameAndUserName(name, userName);

        if (isDuplicate) {
            throw new IllegalStateException("이미 존재하는 운동입니다.");
        }

        return true;
    }

    private boolean validateNotExistWorkoutByNameAndUserName(String name, String userName) {
        boolean isDuplicate = workoutRepository.existsByNameAndUserName(name, userName);

        if (!isDuplicate) {
            throw new IllegalStateException("존재하지 않는 운동입니다.");
        }

        return true;
    }

    private boolean validateNotExistWorkoutById(Long id) {
        boolean isExist = workoutRepository.existsById(id);

        if (!isExist) {
            throw new IllegalStateException("존재하지 않는 운동입니다.");
        }

        return true;
    }

    @Data
    public static class CreateUpdateWorkoutResponse {
        private Workout workout;

        public CreateUpdateWorkoutResponse(Workout workout) {
            this.workout = workout;
        }
    }
}
