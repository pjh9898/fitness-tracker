package study.fitness.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.fitness.domain.Routine;
import study.fitness.dto.RoutinePostRequestDto;
import study.fitness.repository.RoutineRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;

    public List<Routine> findRoutines() {
        return routineRepository.findAll();
    }

    public Routine findRoutineById(Long id) {
        return routineRepository.findById(id).orElse(null);
    }

    public Long createRoutine(RoutinePostRequestDto requestDto, Long userId) {
        Routine routine = requestDto.toEntity(userId);
        validateExistRoutineByNameAndUserId(routine.getName(), userId);

        routineRepository.save(routine);

        return routine.getId();
    }

//    public void updateRoutine(Long id, RoutinePatchRequestDto requestDto) {
//        Routine routine = routineRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Routine not found with id: " + id));
//
//        routine.update(requestDto.getName(), requestDto.getType(), requestDto.getDescription());
//    }
//
//    public Long deleteRoutine(Long id) {
//        validateNotExistRoutineById(id);
//
//        routineRepository.deleteById(id);
//
//        return id;
//    }

    private boolean validateExistRoutineByNameAndUserId(String name, Long userId) {
        boolean isDuplicate = routineRepository.existsByNameAndUserId(name, userId);

        if (isDuplicate) {
            throw new IllegalStateException("이미 존재하는 운동입니다.");
        }

        return true;
    }
}
