package it.malda.school.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudentTest {

    @Test
    void testConstructor() {
        Student actualStudent = new Student();
        actualStudent.setAge("Age");
        actualStudent.setId(123L);
        actualStudent.setName("Name");
        actualStudent.setPhoneNumber("4105551212");
        actualStudent.setSurname("Doe");
        assertEquals("Age", actualStudent.getAge());
        assertEquals(123L, actualStudent.getId().longValue());
        assertEquals("Name", actualStudent.getName());
        assertEquals("4105551212", actualStudent.getPhoneNumber());
        assertEquals("Doe", actualStudent.getSurname());
        assertEquals("Name Doe", actualStudent.getFullName());
    }


}