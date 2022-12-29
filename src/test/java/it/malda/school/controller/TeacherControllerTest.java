package it.malda.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.malda.school.controller.model.TeacherDto;
import it.malda.school.entity.Course;
import it.malda.school.entity.Teacher;
import it.malda.school.mapper.TeacherMapper;
import it.malda.school.mapper.TeacherMapperImpl;
import it.malda.school.service.CourseService;
import it.malda.school.service.TeacherService;
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
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TeacherService teacherService;

    @MockBean
    private TeacherMapper teacherMapper;

    private TeacherMapperImpl teacherMapperBean = new TeacherMapperImpl();

    @MockBean
    private static CourseService courseService;

    @Test
    void testGetList() throws Exception {
        List<Teacher> teacherEntities = List.of(Teacher.builder().id(1L).name("Giuseppe").surname("Maldarelli").subject("Informatica").build());
        List<TeacherDto> teacherDtos = this.teacherMapperBean.toDto(teacherEntities);
        when(teacherService.getList(anyInt())).thenReturn(teacherEntities);
        when(teacherMapper.toDto(teacherEntities)).thenReturn(teacherDtos);
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/teacher")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());

    }

    @Test
    void testGetOne() throws Exception {
        Teacher teacher = Teacher.builder().id(1L).name("Adriano").surname("Addante").subject("Informatica").build();
        when(teacherService.getOne(anyLong())).thenReturn(teacher);
        List<Course> courses = List.of(Course.builder().id(2L).name("Italiano").build(),Course.builder().id(3L).name("Inglese").build());
        when(courseService.findCourseListByTeacher(teacher)).thenReturn(courses);
        TeacherDto teacherDto = this.teacherMapperBean.toDto(teacher);
        when(teacherMapper.toDto(teacher)).thenReturn(teacherDto);
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/teacher/1")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.courses[*]").isNotEmpty());

    }

    @Test
    void testInsert() throws Exception {
        TeacherDto teacherDto = TeacherDto.builder().name("Giuseppe").surname("Maldarelli").subject("Informatica").build();
        Teacher teacherEntity = this.teacherMapperBean.toEntity(teacherDto);
        when(teacherMapper.toEntity(teacherDto)).thenReturn(teacherEntity);
        teacherEntity.setId(1L);
        when(teacherService.insert(any())).thenReturn(teacherEntity);
        TeacherDto teacherDtoResult = this.teacherMapperBean.toDto(teacherEntity);
        when(teacherMapper.toDto((Teacher) any())).thenReturn(teacherDtoResult);
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/teacher")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(teacherDto))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));

    }
    @Test
    void testDelete() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/teacher/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("Deleted Teacher"));
    }

    @Test
    void testUpdate() throws Exception {
        TeacherDto teacherDto = TeacherDto.builder().id(1L).name("Adriano").surname("Addante").subject("Informatica").build();
        Teacher teacherEntity = this.teacherMapperBean.toEntity(teacherDto);
        when(teacherMapper.toEntity((TeacherDto) any())).thenReturn(teacherEntity);
        List<Course> courses = List.of(Course.builder().id(2L).name("Italiano").build(),Course.builder().id(3L).name("Inglese").build());
        when(courseService.findCourseListByTeacher(any())).thenReturn(courses);
        when(teacherService.update(1L,teacherEntity)).thenReturn(teacherEntity);
        TeacherDto teacherDtoResult = this.teacherMapperBean.toDto(teacherEntity);
        when(teacherMapper.toDto(teacherEntity)).thenReturn(teacherDtoResult);
        teacherDtoResult.setCourses(courses.stream().map(Course::getName).collect(Collectors.toList()));
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/teacher/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(teacherDtoResult))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

