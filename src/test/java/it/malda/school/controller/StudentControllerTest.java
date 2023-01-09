package it.malda.school.controller;

import it.malda.school.controller.model.CourseDto;
import it.malda.school.controller.model.StudentDto;
import it.malda.school.entity.Course;
import it.malda.school.entity.Student;
import it.malda.school.entity.Teacher;
import it.malda.school.mapper.CourseMapper;
import it.malda.school.mapper.CourseMapperImpl;
import it.malda.school.mapper.StudentMapper;
import it.malda.school.mapper.StudentMapperImpl;
import it.malda.school.repo.CourseRepository;
import it.malda.school.service.CourseService;
import it.malda.school.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private StudentMapper studentMapper;

    private StudentMapperImpl studentMapperBean = new StudentMapperImpl();

    @Test
    void testGetList() throws Exception {
        List<Student> students = List.of(Student.builder()
                .id(2L)
                .name("Adriano")
                .surname("Addante").build(),
                    Student.builder().id(3L)
                        .name("Gianni")
                        .surname("Maranza").build());
        List<StudentDto> studentDtos = this.studentMapperBean.toDto(students);
        when(studentService.getList(anyInt())).thenReturn(students);
        when(studentMapper.toDto(students)).thenReturn(studentDtos);
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/student")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Adriano"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Gianni"));

    }
}

