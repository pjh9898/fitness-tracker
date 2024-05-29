package study.fitness.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.fitness.domain.Workout;
import study.fitness.dto.WorkoutPatchRequestDto;
import study.fitness.dto.WorkoutPostRequestDto;
import study.fitness.repository.WorkoutRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public List<Workout> findWorkouts() {
        return workoutRepository.findAll();
    }

    public Workout findWorkoutById(Long id) {
        return workoutRepository.findById(id).orElse(null);
    }

    public Long createWorkout(WorkoutPostRequestDto requestDto, String userName) {
        Workout workout = requestDto.toEntity(userName);
        validateExistWorkoutByNameAndUserName(workout.getName(), workout.getUserName());

        workoutRepository.save(workout);

        return workout.getId();
    }

    public void updateWorkout(Long id, WorkoutPatchRequestDto requestDto) {
        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Workout not found with id: " + id));

        workout.update(requestDto.getName(), requestDto.getType(), requestDto.getDescription());
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
