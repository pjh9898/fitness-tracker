package study.fitness.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import study.fitness.domain.Workout;
import study.fitness.domain.WorkoutType;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class WorkoutRepositoryTest {

    @Autowired
    WorkoutRepository workoutRepository;

    @AfterEach
    void clear() {
        workoutRepository.deleteAllInBatch();
    }

    @Test
    public void 운동찾기_이름이랑_유저이름으로() throws Exception {
        //given
        Workout workout = Workout.builder().name("name1").type(WorkoutType.count).userName("userName1").build();

        //when
        Boolean result = workoutRepository.existsByNameAndUserName(workout.getName(), workout.getUserName());

        //then
        Assertions.assertThat(result).isEqualTo(true);

    }


}