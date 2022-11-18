package it.malda.school.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.malda.school.entity.Teacher;
import it.malda.school.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TeacherController.class})
@ExtendWith(SpringExtension.class)
class TeacherControllerTest {
    @Autowired
    private TeacherController teacherController;

    @MockBean
    private TeacherService teacherService;

    /**
     * Method under test: {@link TeacherController#getList(int)}
     */
    @Test
    void testGetList() throws Exception {
        when(teacherService.getList(anyInt())).thenReturn(MappingIterator.emptyIterator());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/teacher");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("size", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(teacherController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"parser\":null}"));
    }

    /**
     * Method under test: {@link TeacherController#getOne(Long)}
     */
    @Test
    void testGetOne() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(123L);
        teacher.setName("Name");
        teacher.setSubject("Hello from the Dreaming Spires");
        teacher.setSurname("Doe");
        when(teacherService.getOne((Long) any())).thenReturn(teacher);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/teacher/{id}", 123L);
        MockMvcBuilders.standaloneSetup(teacherController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"name\":\"Name\",\"surname\":\"Doe\",\"subject\":\"Hello from the Dreaming Spires\"}"));
    }

    /**
     * Method under test: {@link TeacherController#delete(Long)}
     */
    @Test
    void testDelete() throws Exception {
        doNothing().when(teacherService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/teacher/{id}", 123L);
        MockMvcBuilders.standaloneSetup(teacherController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Deleted Teacher"));
    }

    /**
     * Method under test: {@link TeacherController#insert(Teacher)}
     */
    @Test
    void testInsert() throws Exception {
        when(teacherService.getList(anyInt())).thenReturn(MappingIterator.emptyIterator());

        Teacher teacher = new Teacher();
        teacher.setId(123L);
        teacher.setName("Name");
        teacher.setSubject("Hello from the Dreaming Spires");
        teacher.setSurname("Doe");
        String content = (new ObjectMapper()).writeValueAsString(teacher);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/teacher")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(teacherController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"parser\":null}"));
    }
}

