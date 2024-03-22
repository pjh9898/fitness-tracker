package study.fitness.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import study.fitness.domain.Workout;
import study.fitness.domain.WorkoutType;
import study.fitness.dto.WorkoutRequestDto;
import study.fitness.repository.WorkoutRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class WorkoutServiceTest {

    @Autowired
    WorkoutService workoutService;

    @Autowired
    WorkoutRepository workoutRepository;

    @AfterEach
    void clear() {
        workoutRepository.deleteAllInBatch();
    }

    @Test
    void 운동목록_조회() {
        //given
        String name = "name12";
        WorkoutType type = WorkoutType.count;
        String description = "desc1";
        WorkoutRequestDto requestDto = WorkoutRequestDto.of(name, type, description);
        workoutService.createWorkout(requestDto, description);

        //when
        List<Workout> workouts = workoutService.findWorkouts();

        //then
        assertThat(workouts.size()).isEqualTo(1);

    }


    @Test
    @Transactional
    void 운동_생성() {
        //given
        String name = "name12";
        WorkoutType type = WorkoutType.count;
        String description = "desc1";
        WorkoutRequestDto requestDto = WorkoutRequestDto.of(name, type, description);

        //when
        Long id = workoutService.createWorkout(requestDto, description);
        Workout workout = workoutRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        String createdWorkoutName = workout.getName();

        //then
        assertThat(createdWorkoutName).isEqualTo(requestDto.getName());

    }

    @Test
    void 운동_업데이트() {
        //given
        String name = "name123";
        WorkoutType type = WorkoutType.count;
        String description = "desc123";
        String userName = "userName123";
        WorkoutRequestDto requestDto = WorkoutRequestDto.of(name, type, description);
        workoutService.createWorkout(requestDto, userName);

        description = "desc1234";
        WorkoutRequestDto requestDto2 = WorkoutRequestDto.of(name, type, description);

        //when
        WorkoutService.CreateUpdateWorkoutResponse workout = workoutService.updateWorkout(requestDto2, userName);

        //then
        assertThat(workout.getWorkout().getDescription()).isNotEqualTo(requestDto.getDescription());

    }

    @Test
    void 운동_삭제() throws Exception {
        //given
        String name = "name123";
        WorkoutType type = WorkoutType.count;
        String description = "desc123";
        String userName = "userName123";
        WorkoutRequestDto requestDto = WorkoutRequestDto.of(name, type, description);
        Long id = workoutService.createWorkout(requestDto, userName);

        //when
        workoutService.deleteWorkout(id);

        //then
        assertThat(workoutRepository.count()).isEqualTo(0);

    }
}