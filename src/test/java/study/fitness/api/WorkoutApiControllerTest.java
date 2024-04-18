package study.fitness.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import study.fitness.service.WorkoutService;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class WorkoutApiControllerTest {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    void 운동목록API() throws Exception {
//        WorkoutPostRequestDto workout = WorkoutPostRequestDto.of("testName", WorkoutType.COUNT, "testDesc");
//        Long createId = workoutService.createWorkout(workout, "testUserName");
//
//        mockMvc.perform(get("/workout")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect();
//    }


    @Test
    public void 운동등록API() throws Exception {
        Map<String, Object> input = new HashMap<>();
        input.put("name", "testName");
        input.put("type", "COUNT");
        input.put("description", "testDescription");

        mockMvc.perform(post("/workout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateWorkout() {
    }

    @Test
    void deleteWorkout() {
    }
}