package it.malda.school.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {

    @Test
    void testConstructor(){
        Course actualCourse = new Course();
        Teacher teacher = new Teacher();
        teacher.setId(999L);
        teacher.setName("Pippo");
        teacher.setSurname("Inzaghi");
        teacher.setSubject("Calciatore");
        Set<Student> students = new HashSet<>();
        Student student1 = new Student();
        student1.setId(888L);
        student1.setAge("33");
        student1.setName("Adriano");
        student1.setSurname("Addante");
        student1.setPhoneNumber("1234567890");
        student1.setCoursesRegistration(new HashSet<>());
        students.add(student1);
        actualCourse.setId(345L);
        actualCourse.setName("Corso di Calcio");
        actualCourse.setTeacher(teacher);
        actualCourse.setMaxParticipants(25l);
        actualCourse.setStudentRegistration(students);
        assertEquals(345L, actualCourse.getId());
        assertEquals("Corso di Calcio", actualCourse.getName());
        assertEquals(999L, actualCourse.getTeacher().getId());
        assertEquals("Pippo", actualCourse.getTeacher().getName());
        assertEquals("Inzaghi", actualCourse.getTeacher().getSurname());
        assertEquals("Calciatore", actualCourse.getTeacher().getSubject());
        assertEquals(25L, actualCourse.getMaxParticipants());
        assertEquals(888L, actualCourse.getStudentRegistration().iterator().next().getId());
        assertEquals("33", actualCourse.getStudentRegistration().iterator().next().getAge());
        assertEquals("Adriano", actualCourse.getStudentRegistration().iterator().next().getName());
        assertEquals("Addante", actualCourse.getStudentRegistration().iterator().next().getSurname());
        assertEquals("1234567890", actualCourse.getStudentRegistration().iterator().next().getPhoneNumber());
        assertEquals(true, actualCourse.getStudentRegistration().iterator().next().getCoursesRegistration().isEmpty());



    }
}