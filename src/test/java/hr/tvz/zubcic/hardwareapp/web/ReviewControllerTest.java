package hr.tvz.zubcic.hardwareapp.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllReviews() throws Exception {

        String token = getToken();

        this.mockMvc.perform(
                        get("/review")
                                .with(csrf())
                                .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(
                        MediaType.APPLICATION_JSON,
                        StandardCharsets.UTF_8)));
    }


    @Test
    void getAllReviewsByHardwareCode() throws Exception {

        String token = getToken();

        String HARDWARE_CODE_TEST = "1111";

        this.mockMvc.perform(
                get("/review?hardwareCode=" + HARDWARE_CODE_TEST)
                        .with(csrf())
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(
                        MediaType.APPLICATION_JSON,
                        StandardCharsets.UTF_8)))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getAllReviewsWithText() throws Exception {

        String token = getToken();

        String REVIEW_TEXT_SEARCH_TEST = "ok";

        this.mockMvc.perform(
                        get("/review?text=" + REVIEW_TEXT_SEARCH_TEST)
                                .with(csrf())
                                .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(
                        MediaType.APPLICATION_JSON,
                        StandardCharsets.UTF_8)))
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    void getReviewById() throws Exception {

        String token = getToken();

        Long REVIEW_ID_TEST = 1L;

        this.mockMvc.perform(
                        get("/review?id=" + REVIEW_ID_TEST)
                                .with(csrf())
                                .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(
                        MediaType.APPLICATION_JSON,
                        StandardCharsets.UTF_8)));

    }


    private String getToken() throws Exception {

        Map<String, Object> body = new HashMap<>();
        body.put("username", "admin");
        body.put("password", "test");

        MvcResult mvcResult = mockMvc.perform(
                post("/authentication/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
        )
                .andExpect(status().isOk())
                .andReturn();

        String mvcResultResponse = mvcResult.getResponse().getContentAsString();

        mvcResultResponse = mvcResultResponse
                .replace("{\"jwt\":\"", "")
                .replace("\"}","");


        String token = mvcResultResponse;

        return token;
    }
}
