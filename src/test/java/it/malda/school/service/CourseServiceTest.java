package it.malda.school.service;

import it.malda.school.entity.Course;
import it.malda.school.entity.Student;
import it.malda.school.entity.Teacher;
import it.malda.school.exception.EntityNotFoundException;
import it.malda.school.exception.InvalidInputException;
import it.malda.school.repo.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CourseService.class})
@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private StudentService studentService;

    @MockBean
    private TeacherService teacherService;


    @Test
    void testInsert() throws Exception {
        Course course = Course.builder()
                .id(123L)
                .name("Adriano")
                .maxParticipants(25L)
                .teacher(Teacher.builder().id(123L).build())
                .build();
        when(courseRepository.save((Course) any())).thenReturn(course);
        Course saved = courseService.insert(course);
        verify(courseRepository).save((Course) any());
        assertSame(course, saved);
    }

    @Test
    void testGetList() {
        Course course = Course.builder()
                .id(123L)
                .name("Corso di Musica")
                .maxParticipants(25L)
                .teacher(Teacher.builder().id(123L).build())
                .build();
        Set<Student> students = new HashSet<>();
        students.add(Student.builder()
                .id(1L)
                .name("Giuseppe")
                .surname("Maldarelli")
                .coursesRegistration(Set.of(course))
                .build());
        when(studentService.getStudentsByCourse((Course) any())).thenReturn(students);
        when(courseRepository.findAll()).thenReturn(List.of(course));
        List<Course> courses = courseService.getList(100);
        assertTrue(courses.size() == 1);
        assertTrue(courses.get(0).getStudentRegistration().size() == 1);
        assertTrue(courses.get(0).getStudentRegistration().stream().collect(Collectors.toList()).get(0).getId() == students.stream().collect(Collectors.toList()).get(0).getId());
        verify(courseRepository).findAll();
    }

    @Test
    void testGetOne() {
        Course course = Course.builder()
                .id(2L)
                .name("Corso di Italiano")
                .maxParticipants(10L)
                .teacher(Teacher.builder().id(3L).build())
                .build();

        when(studentService.getStudentsByCourse((Course) any())).thenReturn(Set.of(Student.builder().id(1L).build()));
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
        Course one = courseService.getOne(course.getId());
        assertTrue(one.getId() == course.getId());
        assertTrue(one.getStudentRegistration().stream().findFirst().get().getId() == course.getStudentRegistration().stream().findFirst().get().getId());
        verify(courseRepository).findById((Long) any());
    }

    @Test
    public void testGetOneFails_EntityNotFoundException() {
        when(courseRepository.findById(1l)).thenReturn(Optional.empty());
        Throwable exception = assertThrows(EntityNotFoundException.class, () -> courseService.getOne(1l));
        assertEquals("Course not found with id [1]", exception.getMessage());
    }

    @Test
    void testDelete() {
        Course course = Course.builder()
                .id(1234L)
                .name("Corso di Musica")
                .maxParticipants(20L)
                .teacher(Teacher.builder().id(1L).build())
                .build();
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
        doNothing().when(courseRepository).deleteById((Long) any());
        courseService.delete(1234L);
        verify(courseRepository, times(1)).deleteById(1234L);
    }

    @Test
    public void testDeleteFails_EntityNotFoundException() {
        when(courseRepository.findById(1l)).thenReturn(Optional.empty());
        Throwable exception = assertThrows(EntityNotFoundException.class, () -> courseService.delete(1l));
        assertEquals("Course not found with id [1]", exception.getMessage());
    }

    @Test
    void testUpdate() {
        Course courseOld = Course.builder()
                .id(1L)
                .name("Corso di Canto")
                .maxParticipants(20L)
                .teacher(Teacher.builder().id(1L).build())
                .build();

        Course courseNew = Course.builder()
                .id(1L)
                .name("Corso di Ballo")
                .maxParticipants(25L)
                .teacher(Teacher.builder().id(1L).build())
                .build();

        when(courseRepository.findById(courseOld.getId())).thenReturn(Optional.of(courseOld));
        when(studentService.countStudentByCourse(courseOld.getId())).thenReturn(20L);
        when(courseRepository.save((Course) any())).thenReturn(courseNew);
        when(studentService.getStudentsByCourse(courseNew)).thenReturn(Set.of());
        Course update = courseService.update(1L, courseNew);
        assertSame(courseNew.getId(), update.getId());
    }

    @Test
    public void testUpdateFails_EntityNotFoundException() {
        Course course = Course.builder()
                .id(1L)
                .build();
        Throwable exception = assertThrows(EntityNotFoundException.class, () -> courseService.update(1l, course));
        assertEquals("Course not found with id [1]", exception.getMessage());
    }

    @Test
    public void testUpdateFails_InvalidInputException() {
        Course course = Course.builder()
                .id(2L)
                .build();
        when(courseRepository.findById((Long) any())).thenReturn(Optional.of(course));
        Throwable exception = assertThrows(InvalidInputException.class, () -> courseService.update(1l, course));
        assertEquals("Context path ID is different from course.id in JSON body", exception.getMessage());
    }


    @Test
    public void testUpdateFails_InvalidInputException2() {
        Course course = Course.builder()
                .id(1L)
                .name("Corso di Ballo")
                .maxParticipants(2L)
                .teacher(Teacher.builder().id(1L).build())
                .studentRegistration(Set.of(Student.builder().id(2L).build(),
                        Student.builder().id(3L).build(),
                        Student.builder().id(4L).build()))
                .build();
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
        when(studentService.countStudentByCourse(course.getId())).thenReturn(3L);
        Throwable exception = assertThrows(InvalidInputException.class, () -> courseService.update(1L, course));
        assertEquals("The maximum number of participants is " + course.getMaxParticipants(), exception.getMessage());
    }


    @Test
    void testFindCourseListByTeacher() {
        Teacher teacher = Teacher.builder().id(2L).build();
        when(courseRepository.findByTeacherId(teacher.getId())).thenReturn(List.of(Course.builder().id(1L).build()));
        List<Course> courses = courseService.findCourseListByTeacher(teacher);
        assertTrue(courses.get(0).getId() == 1L);
    }

    @Test
    void testAssignTeacher() {
        Teacher teacher = Teacher.builder().id(2L).build();
        Course course = Course.builder().id(1L).build();
        Course clone = course.toBuilder().build();
        clone.setTeacher(teacher);
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
        when(teacherService.getOne((Long) any())).thenReturn(teacher);
        when(courseRepository.save((Course) any())).thenReturn(clone);
        Course result = courseService.assignTeacher(course.getId(), teacher.getId());
        assertEquals(1L, (long) result.getId());
        assertEquals(2L, (long) result.getTeacher().getId());
    }

    @Test
    void testUnassignedTeacher() {
        Course course = Course.builder().id(123L)
                .teacher(Teacher.builder().id(1L).build())
                .build();
        Course clone = course.toBuilder().build();
        clone.setTeacher(null);
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
        when(courseRepository.save((Course) any())).thenReturn(clone);
        courseService.unassignedTeacher(course.getId());
        assertEquals(123L, (long) course.getId());
        assertNull(course.getTeacher());
    }

    @Test
    void testModifyMaxParticipants() {
        Course course = Course.builder().id(123L)
                .maxParticipants(25L)
                .build();
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
        courseService.modifyMaxParticipants(course.getId(), 50L);
        assertEquals(123L, (long) course.getId());
        verify(courseRepository, times(1)).modifyMaxParticipants(123L, 50L);
    }

    @Test
    public void testModifyMaxParticipantsFails_EntityNotFoundException() {
        Course course = Course.builder()
                .id(1L)
                .build();
        Throwable exception = assertThrows(EntityNotFoundException.class, () -> courseService.modifyMaxParticipants(1l, 10L));
        assertEquals("Course not found with id [1]", exception.getMessage());
    }

    @Test
    void testAssignStudent() {
        Course course = Course.builder()
                .id(1L)
                .maxParticipants(20L)
                .studentRegistration(new HashSet<>())
                .build();
        Student student = Student.builder()
                .id(2L)
                .coursesRegistration(new HashSet<>())
                .build();
        Course clone = course.toBuilder().build();
        when(courseRepository.findById(clone.getId())).thenReturn(Optional.of(clone));
        when(studentService.getOne((Long) any())).thenReturn(student);
        when(studentService.countStudentByCourse((Long) any())).thenReturn(1L);
        Course result = courseService.assignStudent(clone.getId(), student.getId());
        assertEquals(1L, (long) result.getId());
        assertEquals(2L, (long) result.getStudentRegistration().stream().findFirst().get().getId());
    }

    @Test
    public void testAssignStudentFails_EntityNotFoundException() {
        Course course = Course.builder()
                .id(1L)
                .maxParticipants(3L)
                .studentRegistration(Set.of(Student.builder().id(2L).build(),
                        Student.builder().id(3L).build(),
                        Student.builder().id(4L).build()))
                .build();
        Student student = Student.builder()
                .id(2L)
                .coursesRegistration(new HashSet<>())
                .build();
        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
        when(studentService.getOne(student.getId())).thenReturn(student);
        when(studentService.countStudentByCourse((Long) any())).thenReturn(3L);
        Throwable exception = assertThrows(InvalidInputException.class, () -> courseService.assignStudent(1l, 2L));
        assertEquals("The maximum number of participants is 3", exception.getMessage());
    }

    @Test
    void testUnassignedStudent() {
        Course course = Course.builder()
                .studentRegistration(new HashSet<>())
                .id(1L)
                .maxParticipants(20L)
                .build();
        Student student = Student.builder()
                .id(2L)
                .coursesRegistration(new HashSet<>())
                .build();
        Course clone = course.toBuilder().build();
        student.getCoursesRegistration().add(clone);
        clone.getStudentRegistration().add(student);
        when(courseRepository.findById(clone.getId())).thenReturn(Optional.of(clone));
        when(studentService.getOne((Long) any())).thenReturn(student);
        Course result = courseService.unassignedStudent(course.getId(), student.getId());
        assertTrue(result.getStudentRegistration().isEmpty());
    }
}