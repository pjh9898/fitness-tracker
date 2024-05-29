package study.fitness.api;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.fitness.domain.Workout;
import study.fitness.dto.WorkoutPatchRequestDto;
import study.fitness.dto.WorkoutPostRequestDto;
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
    public ResponseEntity<String> saveWorkout(@RequestBody @Validated WorkoutPostRequestDto requestDto, String userName) {
        workoutService.createWorkout(requestDto, userName);

        return ResponseEntity.ok(requestDto.getName());
    }

    @PatchMapping("/workout/{id}")
    @Transactional
    public ResponseEntity<Workout> updateWorkout( @Validated @PathVariable Long id, @RequestBody WorkoutPatchRequestDto requestDto) {
        workoutService.updateWorkout(id, requestDto);
        Workout workout = workoutService.findWorkoutById(id);

        return ResponseEntity.ok(workout);
    }

    @DeleteMapping("/workout/{id}")
    public ResponseEntity<Long> deleteWorkout(@PathVariable Long id) {
        workoutService.deleteWorkout(id);

        return ResponseEntity.ok(id);
    }
}
