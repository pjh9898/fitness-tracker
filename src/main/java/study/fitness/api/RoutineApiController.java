package study.fitness.api;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.fitness.domain.Routine;
import study.fitness.dto.RoutinePostRequestDto;
import study.fitness.service.RoutineService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoutineApiController {

    private final RoutineService routineService;

    @GetMapping("/routine")
    public ResponseEntity<List<Routine>> searchRoutine() {
        return ResponseEntity.ok(routineService.findRoutines());
    }

    @PostMapping("/routine")
    public ResponseEntity<String> saveRoutine(@RequestBody @Validated RoutinePostRequestDto requestDto, Long userId) {
        routineService.createRoutine(requestDto, userId);

        return ResponseEntity.ok(requestDto.getName());
    }

//    @PatchMapping("/routine/{id}")
//    @Transactional
//    public ResponseEntity<Routine> updateRoutine( @Validated @PathVariable Long id, @RequestBody RoutinePatchRequestDto requestDto) {
//        routineService.updateRoutine(id, requestDto);
//        Routine routine = routineService.findRoutineById(id);
//
//        return ResponseEntity.ok(routine);
//    }
//
//    @DeleteMapping("/routine/{id}")
//    public ResponseEntity<Long> deleteRoutine(@PathVariable Long id) {
//        routineService.deleteRoutine(id);
//
//        return ResponseEntity.ok(id);
//    }
}
