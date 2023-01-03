package it.malda.school.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
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
public class StudentIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void postSuccess() throws Exception {
        StudentDto studentDto = StudentDto.builder()
                .name("Adriano")
                .surname("Addante")
                .age("24")
                .build();
        MvcResult resultPost = mockMvc.perform(MockMvcRequestBuilders.post("/api/student")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(studentDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Adriano"))
                .andReturn();

        Integer newlySavedId = JsonPath.read(resultPost.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/" + newlySavedId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(String.valueOf(newlySavedId)))
                .andExpect(jsonPath("$.surname").value("Addante"));
    }

    @Test
    @Sql(statements = "INSERT INTO student(name,surname,age,phone_number) VALUES ('Jacob','Jacob Graham',33,'3-508-718-0571');")
    void deleteSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/student/1")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Deleted Student"))
                .andReturn();
    }

    @Test
    @Sql(statements = "INSERT INTO student(id,name,surname,age,phone_number) VALUES (10,'Jacob','Jacob Graham',33,'3-508-718-0571');")
    void updateSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/10"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.name").value("Jacob"))
                .andExpect(jsonPath("$.surname").value("Jacob Graham"))
                .andExpect(jsonPath("$.age").value("33"));
        StudentDto studentDto = StudentDto.builder()
                .id(10L)
                .name("Adriano")
                .surname("Addante")
                .age("24")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.put("/api/student/10")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(studentDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Adriano"))
                .andExpect(jsonPath("$.phoneNumber").isEmpty())
                .andReturn();
    }

    @Test
    @Sql(statements = {"INSERT INTO student(id,name,surname,age,phone_number) VALUES (20,'Laura','Non c''è',33,'123123123');",
            "INSERT INTO student(id,name,surname,age,phone_number) VALUES (21,'Adriano','Addante',24,'3334441112');"
    })
    void getSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/student"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/student/20"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(20L))
                .andExpect(jsonPath("$.name").value("Laura"))
                .andExpect(jsonPath("$.surname").value("Non c'è"))
                .andExpect(jsonPath("$.age").value("33"));
    }
}

