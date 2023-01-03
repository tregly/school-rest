package it.malda.school.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import it.malda.school.controller.model.TeacherDto;
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
public class TeacherIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql(statements = "INSERT INTO teacher(name,surname,subject) VALUES('Chester','Matthews','Economics')")
    void postSuccess() throws Exception {
        TeacherDto teacherDto = TeacherDto.builder()
                .name("Giuseppe")
                .surname("Maldarelli")
                .subject("Informatichese")
                .build();
        MvcResult resultPost = mockMvc.perform(MockMvcRequestBuilders.post("/api/teacher")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(teacherDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subject").value("Informatichese"))
                .andReturn();

        Integer newlySavedId = JsonPath.read(resultPost.getResponse().getContentAsString(), "$.id");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/" + newlySavedId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(String.valueOf(newlySavedId)))
                .andExpect(jsonPath("$.surname").value("Maldarelli"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(statements = "INSERT INTO teacher(id,name,surname,subject) VALUES(2,'Lala','Land','Music');")
    void deleteSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/teacher/2")
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Deleted Teacher"))
                .andReturn();
    }

    @Test
    @Sql(statements = {"INSERT INTO teacher(id,name,surname,subject) VALUES(10,'Lala','Land','Music');",
            "INSERT INTO teacher(id,name,surname,subject) VALUES(11,'Chester','Matthews','Economics');"
    })
    void getSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/11"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(11L))
                .andExpect(jsonPath("$.name").value("Chester"))
                .andExpect(jsonPath("$.surname").value("Matthews"))
                .andExpect(jsonPath("$.subject").value("Economics"));
    }

    @Test
    @Sql(statements = "INSERT INTO teacher(id,name,surname,subject) VALUES(8,'Chester','Matthews','Economics')")
    void postUpdate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/teacher/8"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(8L))
                .andExpect(jsonPath("$.name").value("Chester"))
                .andExpect(jsonPath("$.surname").value("Matthews"))
                .andExpect(jsonPath("$.subject").value("Economics"))
                .andReturn();

        TeacherDto teacherDto = TeacherDto.builder()
                .id(8L)
                .name("Adriano")
                .surname("Addante")
                .subject("Informatics")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/teacher/8")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(teacherDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(8L))
                .andExpect(jsonPath("$.name").value("Adriano"))
                .andExpect(jsonPath("$.surname").value("Addante"))
                .andExpect(jsonPath("$.subject").value("Informatics"))
                .andReturn();
    }


    @Test
    @Sql(statements = "INSERT INTO teacher(name,surname,subject) VALUES('Chester','Matthews','Economics')")
    void postFails() throws Exception {
        TeacherDto teacherDto = TeacherDto.builder()
                .build();
        MvcResult resultPost = mockMvc.perform(MockMvcRequestBuilders.post("/api/teacher")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(teacherDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

    }
}
