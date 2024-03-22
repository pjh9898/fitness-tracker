package study.fitness.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.fitness.domain.Workout;
import study.fitness.dto.WorkoutRequestDto;
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
    public ResponseEntity<String> saveWorkout(@RequestBody @Validated WorkoutRequestDto requestDto, String userName) {
        workoutService.createWorkout(requestDto, userName);

        return ResponseEntity.ok(requestDto.getName());
    }

    @PatchMapping("/workout")
    public ResponseEntity<String> updateWorkout(@RequestBody @Validated WorkoutRequestDto requestDto, String userName) {
        workoutService.updateWorkout(requestDto, userName);

        return ResponseEntity.ok(requestDto.getName());
    }

    @DeleteMapping("/workout/{workoutId}")
    public ResponseEntity<Long> deleteWorkout(@PathVariable Long workoutId) {
        workoutService.deleteWorkout(workoutId);

        return ResponseEntity.ok(workoutId);
    }
}
