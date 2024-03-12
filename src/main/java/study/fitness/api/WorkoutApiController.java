package study.fitness.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.fitness.domain.Workout;
import study.fitness.domain.WorkoutType;
import study.fitness.dto.CreateWorkoutRequestDto;
import study.fitness.dto.UpdateWorkoutRequestDto;
import study.fitness.service.WorkoutService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkoutApiController {

    private final WorkoutService workoutService;

    @GetMapping("/workout")
    public ResponseEntity<List<Workout>> searchWorkout() {
        return ResponseEntity.ok(workoutService.findWorkouts());
    }

    @PostMapping("/workout")
    public ResponseEntity<String> saveWorkout(@RequestBody @Validated CreateWorkoutRequestDto requestDto, String userName) {
        String name = requestDto.getName();
        WorkoutType type = requestDto.getType();
        String desc = requestDto.getDesc();

        Workout workout = Workout.of(name, type, desc);

        workoutService.createWorkout(workout, userName);

        return ResponseEntity.ok(name);
    }

    @PatchMapping("/workout/{workoutId}")
    public ResponseEntity<String> updateWorkout(@RequestBody @Validated UpdateWorkoutRequestDto requestDto, String userName) {
        String name = requestDto.getName();
        WorkoutType type = requestDto.getType();
        String desc = requestDto.getDesc();

        Workout workout = Workout.of(name, type, desc);

        workoutService.updateWorkout(workout, userName);

        return ResponseEntity.ok(name);
    }

    @DeleteMapping("/workout/{workoutId}")
    public ResponseEntity<Long> deleteWorkout(@PathVariable Long workoutId) {
        workoutService.deleteWorkout(workoutId);

        return ResponseEntity.ok(workoutId);
    }
}
