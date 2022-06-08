package hr.tvz.zubcic.hardwareapp.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.zubcic.hardwareapp.hardware.Hardware;
import hr.tvz.zubcic.hardwareapp.hardware.HardwareCommand;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HardwareControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getAllHardware() throws Exception {

        String token = getToken();

        this.mockMvc.perform(
                        get("/hardware")
                                .with(csrf())
                                .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(
                        MediaType.APPLICATION_JSON,
                        StandardCharsets.UTF_8)));

    }


    @Test
    void getHardwareByCode() throws Exception {

        String CODE_HARDWAREE_TEST = "2222";

        this.mockMvc.perform(
                get("/hardware/" + CODE_HARDWAREE_TEST)
                        .with(
                                user("admin")
                                        .password("test")
                                        .roles("ADMIN")
                        )
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(
                        MediaType.APPLICATION_JSON,
                        StandardCharsets.UTF_8)));
    }

    @DirtiesContext
    @Test
    void save() throws Exception {

        String CODE_TEST = "6666";
        String NAME_TEST = "test_hardware";
        BigDecimal PRICE_TEST = BigDecimal.valueOf(300);
        Hardware.Type TYPE_TEST = Hardware.Type.RAM;
        Integer STOCK_TEST = 50;

        HardwareCommand hardwareCommandTest = new HardwareCommand(
                NAME_TEST,
                CODE_TEST,
                PRICE_TEST,
                TYPE_TEST,
                STOCK_TEST
        );

        this.mockMvc.perform(
                post("/hardware")
                        .with(
                                user("admin")
                                        .password("test")
                                        .roles("ADMIN")
                        )
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hardwareCommandTest))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(new MediaType(
                        MediaType.APPLICATION_JSON,
                        StandardCharsets.UTF_8)))
                .andExpect(jsonPath("$.code").value(CODE_TEST));
    }

    @DirtiesContext
    @Test
    void update() throws Exception {

        String CODE_TEST = "4444";
        String NAME_TEST = "test_hardware";
        BigDecimal PRICE_TEST = BigDecimal.valueOf(300);
        Hardware.Type TYPE_TEST = Hardware.Type.RAM;
        Integer STOCK_TEST = 100;

        HardwareCommand hardwareCommandTest = new HardwareCommand(
                NAME_TEST,
                CODE_TEST,
                PRICE_TEST,
                TYPE_TEST,
                STOCK_TEST
        );

        this.mockMvc.perform(
                        put("/hardware/" + CODE_TEST)
                                .with(
                                        user("admin")
                                                .password("test")
                                                .roles("ADMIN")
                                )
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(hardwareCommandTest))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }


    @DirtiesContext
    @Test
    void update_validation() throws Exception {

        String CODE_TEST = "4444";
        String NAME_TEST = "test_hardware";
        BigDecimal PRICE_TEST = BigDecimal.valueOf(300);
        Hardware.Type TYPE_TEST = Hardware.Type.RAM;
        Integer STOCK_TEST = 250;

        HardwareCommand hardwareCommandTest = new HardwareCommand(
                NAME_TEST,
                CODE_TEST,
                PRICE_TEST,
                TYPE_TEST,
                STOCK_TEST
        );

        this.mockMvc.perform(
                        put("/hardware/" + CODE_TEST)
                                .with(
                                        user("admin")
                                                .password("test")
                                                .roles("ADMIN")
                                )
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(hardwareCommandTest))
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }




    @DirtiesContext
    @Test
    void delete() throws Exception {

        String CODE_HARDWAREE_TEST = "2222";

        this.mockMvc.perform(
                        MockMvcRequestBuilders.delete("/hardware/" + CODE_HARDWAREE_TEST)
                                .with(
                                        user("admin")
                                                .password("test")
                                                .roles("ADMIN")
                                )
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());

        this.mockMvc.perform(
                        get("/hardware/" + CODE_HARDWAREE_TEST)
                                .with(
                                        user("admin")
                                                .password("test")
                                                .roles("ADMIN")
                                )
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }


    private String getToken() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("username", "admin");
        body.put("password", "test");

        MvcResult mvcResult = mockMvc.perform(
                 post("/authentication/login")
                         .contentType(MediaType.APPLICATION_JSON)
                         .content(objectMapper.writeValueAsString(body))
         ).andExpect(status().isOk()).andReturn();

        String mvcResultResponse = mvcResult.getResponse().getContentAsString();

        mvcResultResponse = mvcResultResponse
                .replace("{\"jwt\":\"", "")
                .replace("\"}","");


        String token = mvcResultResponse;
        return token;
    }

}
