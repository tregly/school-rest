package it.malda.school.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.malda.school.entity.Teacher;
import it.malda.school.repo.TeacherRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TeacherService.class})
@ExtendWith(SpringExtension.class)
class TeacherServiceTest {
    @MockBean
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;

    /**
     * Method under test: {@link TeacherService#insert(Teacher)}
     */
    @Test
    void testInsert() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(123L);
        teacher.setName("Name");
        teacher.setSubject("Hello from the Dreaming Spires");
        teacher.setSurname("Doe");
        when(teacherRepository.save((Teacher) any())).thenReturn(teacher);

        Teacher teacher1 = new Teacher();
        teacher1.setId(123L);
        teacher1.setName("Name");
        teacher1.setSubject("Hello from the Dreaming Spires");
        teacher1.setSurname("Doe");
        assertSame(teacher, teacherService.insert(teacher1));
        verify(teacherRepository).save((Teacher) any());
    }

    /**
     * Method under test: {@link TeacherService#getList(int)}
     */
    @Test
    void testGetList() {
        when(teacherRepository.findAll()).thenReturn(new ArrayList<>());
        teacherService.getList(3);
        verify(teacherRepository).findAll();
    }

    /**
     * Method under test: {@link TeacherService#getOne(Long)}
     */
    @Test
    void testGetOne() {
        Teacher teacher = new Teacher();
        teacher.setId(123L);
        teacher.setName("Name");
        teacher.setSubject("Hello from the Dreaming Spires");
        teacher.setSurname("Doe");
        Optional<Teacher> ofResult = Optional.of(teacher);
        when(teacherRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(teacher, teacherService.getOne(123L));
        verify(teacherRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link TeacherService#delete(Long)}
     */
    @Test
    void testDelete() throws Exception {
        doNothing().when(teacherRepository).deleteById((Long) any());
        teacherService.delete(123L);
        verify(teacherRepository).deleteById((Long) any());
    }
}

