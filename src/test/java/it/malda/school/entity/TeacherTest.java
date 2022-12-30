package it.malda.school.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeacherTest {

    @Test
    void testConstructor(){
        Teacher actualTeacher = new Teacher();
        actualTeacher.setId(123L);
        actualTeacher.setName("Giannino");
        actualTeacher.setSurname("Carri");
        actualTeacher.setSubject("Italiano");
        assertEquals(123L, actualTeacher.getId());
        assertEquals("Giannino", actualTeacher.getName());
        assertEquals("Carri", actualTeacher.getSurname());
        assertEquals("Italiano", actualTeacher.getSubject());
        assertEquals("Giannino" + " " + "Carri", actualTeacher.getFullName());
    }
}
