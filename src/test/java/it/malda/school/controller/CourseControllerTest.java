package it.malda.school.controller;

import it.malda.school.controller.model.CourseDto;
import it.malda.school.entity.Course;
import it.malda.school.entity.Student;
import it.malda.school.entity.Teacher;
import it.malda.school.mapper.CourseMapper;
import it.malda.school.mapper.CourseMapperImpl;
import it.malda.school.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CourseService courseService;

    @MockBean
    private CourseMapper courseMapper;

    private CourseMapperImpl courseMapperBean = new CourseMapperImpl();

    @Test
    void testGetList() throws Exception {
        List<Course> courseEntities = List.of(Course.builder()
                .id(1L)
                .name("Corso di Canto")
                .maxParticipants(20L)
                .studentRegistration(Set.of(Student.builder()
                        .id(2L)
                        .name("Adriano")
                        .surname("Addante")
                        .build()))
                .teacher(Teacher.builder()
                        .name("Gina")
                        .surname("La Postina")
                        .subject("Comunicazione")
                        .build())
                .build());
        List<CourseDto> courseDtos = this.courseMapperBean.toDto(courseEntities);
        when(courseService.getList(anyInt())).thenReturn(courseEntities);
        when(courseMapper.toDto(courseEntities)).thenReturn(courseDtos);
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/course")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numberOfParticipants").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].teacherName").value("Gina La Postina"));

    }

    @Test
    void testGetOne() throws Exception {
        Course courseEntity = Course.builder()
                .id(1L)
                .name("Corso Ninja")
                .maxParticipants(20L)
                .studentRegistration(Set.of(Student.builder()
                        .id(2L)
                        .name("Adriano")
                        .surname("Addante")
                        .build()))
                .teacher(Teacher.builder()
                        .name("Juzo")
                        .surname("Hatake")
                        .subject("Comunicazione")
                        .build())
                .build();
        CourseDto courseDto = this.courseMapperBean.toDto(courseEntity);
        when(courseService.getOne(anyLong())).thenReturn(courseEntity);
        when(courseMapper.toDto(courseEntity)).thenReturn(courseDto);
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/course/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfParticipants").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.participants[0]").value("Adriano Addante"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherName").value("Juzo Hatake"));

    }


    /*@Test
    void testInsert() throws Exception {
        CourseDto courseDto = CourseDto.builder()
                .id(1L)
                .name("Corso di Recitazione")
                .maxParticipants(25L)
                .build();
        Course courseEntity = this.courseMapperBean.toEntity(courseDto);
        when(courseMapper.toEntity(courseDto)).thenReturn(courseEntity);
        courseEntity.setTeacher(Teacher.builder()
                .name("Juzo")
                .surname("Hatake")
                .subject("Comunicazione")
                .build());
        courseEntity.setStudentRegistration(Set.of(Student.builder()
                .id(2L)
                .name("Adriano")
                .surname("Addante")
                .build()));
        when(courseService.insert((Course) any())).thenReturn(courseEntity);
        when(courseMapper.toDto((Course) any())).thenReturn(courseDto);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/course")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(courseDto))
                )
                .andDo(print())
                .andExpect(status().isOk());
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfParticipants").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.participants[0]").value("Adriano Addante"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacherName").value("Juzo Hatake"));

    }
*/

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void assignTeacher() {
    }

    @Test
    void unassignTeacher() {
    }

    @Test
    void assignStudent() {
    }

    @Test
    void unassignStudent() {
    }

    @Test
    void modifyMaxParticipants() {
    }
}
