package it.malda.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.malda.school.entity.Student;
import it.malda.school.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
class StudentControllerTest {
    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentService studentService;

    /**
     * Method under test: {@link StudentController#delete(Long)}
     */
    @Test
    void testDelete() throws Exception {
        // Arrange
        // TODO: Populate arranged inputs
        Object[] uriVariables = new Object[]{123L};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/student/{id}", uriVariables);
        Object[] controllers = new Object[]{studentController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link StudentController#getList(int)}
     */
    @Test
    void testGetList() throws Exception {
        // Arrange
        // TODO: Populate arranged inputs
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/student", uriVariables);
        String[] values = new String[]{String.valueOf(2)};
        MockHttpServletRequestBuilder requestBuilder = getResult.param("size", values);
        Object[] controllers = new Object[]{studentController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link StudentController#getOne(Long)}
     */
    @Test
    void testGetOne() throws Exception {
        // Arrange
        // TODO: Populate arranged inputs
        Object[] uriVariables = new Object[]{123L};
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/student/{id}", uriVariables);
        Object[] controllers = new Object[]{studentController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link StudentController#insert(Student)}
     */
    @Test
    void testInsert() throws Exception {
        // Arrange
        // TODO: Populate arranged inputs
        Object[] uriVariables = new Object[]{};
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/student", uriVariables)
                .contentType(MediaType.APPLICATION_JSON);

        Student student = new Student();
        student.setAge("Age");
        student.setId(123L);
        student.setName("Name");
        student.setPhoneNumber("4105551212");
        student.setSurname("Doe");

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(student));
        Object[] controllers = new Object[]{studentController};
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        System.out.println("IO");
        // TODO: Add assertions on result
    }
}

