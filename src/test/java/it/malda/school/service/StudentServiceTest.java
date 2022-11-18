package it.malda.school.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import it.malda.school.entity.Student;
import it.malda.school.repo.StudentRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
class StudentServiceTest {
    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    /**
     * Method under test: {@link StudentService#insert(Student)}
     */
    @Test
    void testInsert() throws Exception {
        Student student = new Student();
        student.setAge("Age");
        student.setId(123L);
        student.setName("Name");
        student.setPhoneNumber("4105551212");
        student.setSurname("Doe");
        when(studentRepository.save((Student) any())).thenReturn(student);

        Student student1 = new Student();
        student1.setAge("Age");
        student1.setId(123L);
        student1.setName("Name");
        student1.setPhoneNumber("4105551212");
        student1.setSurname("Doe");
        assertSame(student, studentService.insert(student1));
        verify(studentRepository).save((Student) any());
    }

    /**
     * Method under test: {@link StudentService#getList(int)}
     */
    @Test
    void testGetList() {
        when(studentRepository.findAll()).thenReturn(new ArrayList<>());
        studentService.getList(3);
        verify(studentRepository).findAll();
    }

    /**
     * Method under test: {@link StudentService#getOne(Long)}
     */
    @Test
    void testGetOne() {
        Student student = new Student();
        student.setAge("Age");
        student.setId(123L);
        student.setName("Name");
        student.setPhoneNumber("4105551212");
        student.setSurname("Doe");
        Optional<Student> ofResult = Optional.of(student);
        when(studentRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(student, studentService.getOne(123L));
        verify(studentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link StudentService#delete(Long)}
     */
    @Test
    void testDelete() throws Exception {
        doNothing().when(studentRepository).deleteById((Long) any());
        studentService.delete(123L);
        verify(studentRepository).deleteById((Long) any());
    }
}

