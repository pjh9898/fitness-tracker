package study.fitness.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import study.fitness.service.WorkoutService;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class WorkoutApiControllerTest {

    @Autowired
    WorkoutService workoutService;

    @Test
    void searchWorkout() {
    }

    @Test
    void saveWorkout() {
    }

    @Test
    void updateWorkout() {
    }

    @Test
    void deleteWorkout() {
    }
}