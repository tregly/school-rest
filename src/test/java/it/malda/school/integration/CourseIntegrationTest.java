package it.malda.school.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import it.malda.school.controller.model.CourseDto;
import it.malda.school.controller.model.StudentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "DEVLOCAL")
class CourseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql(statements = {"INSERT INTO course(id,name,max_participants) VALUES (10,'Psicologia',25);",
            "INSERT INTO course(id,name,max_participants) VALUES (11,'Mattanza',25);"})
    void getSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/course"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/course/11"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(jsonPath("$.maxParticipants").value(25L))
                .andExpect(jsonPath("$.name").value("Mattanza"));
    }

    @Test
    @Sql(statements = "INSERT INTO course(id,name,max_participants) VALUES (15,'Lettura',25);")
    void deleteSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/course/15")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Deleted Course"))
                .andReturn();
    }

    @Test
    @Sql(statements = "INSERT INTO course(id,name,max_participants) VALUES (1,'Sociology',25);")
    void putSuccess() throws Exception {
        CourseDto courseDto = CourseDto.builder()
                .id(1L)
                .name("Corso di Recitazione")
                .maxParticipants(25L)
                .build();
        //UPDATE
        mockMvc.perform(MockMvcRequestBuilders.put("/api/course/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(courseDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Corso di Recitazione"))
                .andReturn();
    }
}
