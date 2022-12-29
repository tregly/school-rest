package it.malda.school.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.malda.school.controller.model.TeacherDto;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TeacherController.class)
class TeacherControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TeacherService teacherService;

    @MockBean()
    private  TeacherMapper teacherMapper;

    private TeacherMapperImpl teacherMappeBean = new TeacherMapperImpl();

    @MockBean
    private static CourseService courseService;
    /**
     * Method under test: {@link TeacherController#getList(int)}
     */
    @Test
    void testGetList() throws Exception {
        List<Teacher> teacherEntities = List.of(Teacher.builder().id(1L).name("Giuseppe").surname("Maldarelli").subject("Informatica").build());
        List<TeacherDto> teacherDtos = this.teacherMappeBean.toDto(teacherEntities);
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
    void testCreateTeacher() throws Exception {
        TeacherDto teacherDto = TeacherDto.builder().name("Giuseppe").surname("Maldarelli").subject("Informatica").build();
        Teacher teacherEntity = this.teacherMappeBean.toEntity(teacherDto);
        when(teacherMapper.toEntity(teacherDto)).thenReturn(teacherEntity);
        teacherEntity.setId(1L);
        when(teacherService.insert((Teacher) any())).thenReturn(teacherEntity);
        TeacherDto teacherDtoResult = this.teacherMappeBean.toDto(teacherEntity);
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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

