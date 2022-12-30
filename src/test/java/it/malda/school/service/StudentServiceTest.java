package it.malda.school.service;

import it.malda.school.entity.Course;
import it.malda.school.entity.Student;
import it.malda.school.entity.Teacher;
import it.malda.school.exception.EntityNotFoundException;
import it.malda.school.exception.ForbiddenInputException;
import it.malda.school.exception.InvalidInputException;
import it.malda.school.repo.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {StudentService.class})
@SpringBootTest
class StudentServiceTest {
    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Test
    void testInsert() throws Exception {
        Student student = Student.builder()
                .id(123L)
                .name("Adriano")
                .surname("Addante")
                .phoneNumber("123456789")
                .age("24")
                .build();
        when(studentRepository.save((Student) any())).thenReturn(student);
        Student saved =  studentService.insert(student);
        verify(studentRepository).save((Student) any());
        assertSame(student, saved);
    }

    @Test
    public void testInsert_ForbiddenInputException() {
        Student student = Student.builder()
                .id(123L)
                .name(null)
                .surname("")
                .phoneNumber("123456789")
                .age("24")
                .build();
        when(studentRepository.save((Student) any())).thenReturn(student);
        Throwable exception = assertThrows(ForbiddenInputException.class, () -> studentService.insert(student));
        assertEquals("Student should not be null!", exception.getMessage());
    }

    @Test
    void testGetList() {
        Student student = Student.builder()
                .id(123L)
                .build();
        when(studentRepository.findAll()).thenReturn(List.of(student));
        List<Student> students = studentService.getList(100);
        assertSame(students, studentRepository.findAll());
    }

    @Test
    void testGetOne() {
        Student student = Student.builder()
                .id(123L)
                .name("Adriano")
                .surname("Addante")
                .phoneNumber("123456789")
                .age("24")
                .build();
        when(studentRepository.findById((Long) any())).thenReturn(Optional.of(student));
        assertSame(student, studentService.getOne(123L));
        verify(studentRepository).findById((Long) any());
    }

    @Test
    void testGetOneFails_EntityNotFoundException() {
        Student student = Student.builder()
                .id(123L)
                .name("Adriano")
                .surname("Addante")
                .phoneNumber("123456789")
                .age("24")
                .build();
        Throwable exception = assertThrows(EntityNotFoundException.class, () -> studentService.getOne(123L));
        assertEquals("Student not found with id [123]", exception.getMessage());
    }

    @Test
    void testDelete() throws Exception {
        Student student = Student.builder()
                .id(123L)
                .name("Adriano")
                .surname("Addante")
                .phoneNumber("123456789")
                .age("24")
                .build();
        when(studentRepository.findById((Long) any())).thenReturn(Optional.of(student));
        doNothing().when(studentRepository).deleteById((Long) any());
        studentService.delete(123L);
        verify(studentRepository,times(1)).deleteById(123L);
    }

    @Test
    void testDeleteFails_EntityNotFoundException() {
        Student student = Student.builder()
                .id(123L)
                .name("Adriano")
                .surname("Addante")
                .phoneNumber("123456789")
                .age("24")
                .build();
        Throwable exception = assertThrows(EntityNotFoundException.class, () -> studentService.delete(123L));
        assertEquals("Student not found with id [123]", exception.getMessage());
    }

    @Test
    void testUpdate() {
        Student studentOld = Student.builder()
                .id(123L)
                .name("Adriano")
                .surname("Addante")
                .phoneNumber("123456789")
                .age("24")
                .coursesRegistration(Set.of(Course.builder().id(1L).build()))
                .build();

        Student studentNew = Student.builder()
                .id(123L)
                .name("Adri")
                .surname("Add")
                .phoneNumber("987654321")
                .age("24")
                .coursesRegistration(Set.of(Course.builder().id(1L).build()))
                .build();
        when(studentRepository.findById((Long) any())).thenReturn(Optional.of(studentOld));
        when(studentRepository.save((Student) any())).thenReturn(studentNew);
        Student update = studentService.update(123L, studentNew);
        assertSame(studentNew.getId(), update.getId());
    }

    @Test
    public void testUpdateFails_InvalidInputException() {
        Student student = Student.builder()
                .id(123L)
                .name("Adriano")
                .surname("Addante")
                .phoneNumber("123456789")
                .age("24")
                .build();
        Throwable exception = assertThrows(InvalidInputException.class, () -> studentService.update(1L,student));
        assertEquals("Context path ID is different from student.id in JSON body", exception.getMessage());
    }

    @Test
    void testGetStudentsByCourse() {
        Course course = Course.builder()
                .id(10L)
                .name("Corso di Canto")
                .maxParticipants(20L)
                .teacher(Teacher.builder().id(1L).build())
                .studentRegistration(Set.of(Student.builder().id(1L).build()))
                .build();
        when(studentRepository.findByCoursesRegistrationId((Long) any())).thenReturn(course.getStudentRegistration());
        studentService.getStudentsByCourse(course);
        verify(studentRepository).findByCoursesRegistrationId((Long) any());
    }

    @Test
    void testCountStudentByCourse() {
        Course course = Course.builder()
                .id(2L)
                .name("Corso di Canto")
                .maxParticipants(20L)
                .teacher(Teacher.builder().id(3L).build())
                .studentRegistration(Set.of(Student.builder().id(4L).build()))
                .build();
        when(studentRepository.countByCoursesRegistrationId((Long) any())).thenReturn((long) course.getStudentRegistration().size());
        studentService.countStudentByCourse(course.getId());
        verify(studentRepository).countByCoursesRegistrationId((Long) any());
    }
}

