package it.malda.school.service;

import it.malda.school.entity.Teacher;
import it.malda.school.exception.InvalidInputException;
import it.malda.school.repo.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {TeacherService.class})
@SpringBootTest
class TeacherServiceTest {
    @MockBean
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;

    @Test
    void testInsert() throws Exception {
        Teacher teacher = Teacher.builder()
                .id(123L)
                .name("Laura")
                .surname("Biagiotti")
                .subject("Scienze")
                .build();
        when(teacherRepository.save((Teacher) any())).thenReturn(teacher);
        Teacher saved = teacherService.insert(teacher);
        verify(teacherRepository).save((Teacher) any());
        assertSame(teacher, saved);
    }

    @Test
    void testInsert_InvalidInputException() {
        Teacher teacher = Teacher.builder()
                .id(123L)
                .name("")
                .surname(null)
                .subject("Scienze")
                .build();
        Throwable exception = assertThrows(InvalidInputException.class, () -> teacherService.insert(teacher));
        assertEquals("Teacher should not be null!", exception.getMessage());
    }

    @Test
    void testGetList() {
        when(teacherRepository.findAll()).thenReturn(new ArrayList<>());
        teacherService.getList(3);
        verify(teacherRepository).findAll();
    }

    @Test
    void testGetOne() {
        Teacher teacher = Teacher.builder()
                .id(123L)
                .name("Laura")
                .surname("Biagiotti")
                .subject("Scienze")
                .build();
        when(teacherRepository.findById((Long) any())).thenReturn(Optional.of(teacher));
        assertSame(teacher, teacherService.getOne(123L));
        verify(teacherRepository).findById((Long) any());
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(teacherRepository).deleteById((Long) any());
        teacherService.delete(123L);
        verify(teacherRepository).deleteById((Long) any());
    }

    @Test
    void testUpdate() {
        Teacher teacherOld = Teacher.builder()
                .id(123L)
                .name("Laura")
                .surname("Biagiotti")
                .subject("Scienze")
                .build();

        Teacher teacherNew = Teacher.builder()
                .id(123L)
                .name("Lau")
                .surname("Biagio")
                .subject("Musica")
                .build();
        when(teacherRepository.findById((Long) any())).thenReturn(Optional.of(teacherOld));
        when(teacherRepository.save((Teacher) any())).thenReturn(teacherNew);
        Teacher update = teacherService.update(123L, teacherNew);
        assertSame(teacherNew.getId(), update.getId());
    }

    @Test
    void testUpdate_InvalidInputException() {
        Teacher teacher = Teacher.builder()
                .id(123L)
                .name("")
                .surname(null)
                .subject("Scienze")
                .build();
        Throwable exception = assertThrows(InvalidInputException.class, () -> teacherService.update(1L, teacher));
        assertEquals("Context path ID is different from teacher.id in JSON body", exception.getMessage());
    }
}

