package it.malda.school.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Student}
     *   <li>{@link Student#setAge(String)}
     *   <li>{@link Student#setId(Long)}
     *   <li>{@link Student#setName(String)}
     *   <li>{@link Student#setPhoneNumber(String)}
     *   <li>{@link Student#setSurname(String)}
     *   <li>{@link Student#getAge()}
     *   <li>{@link Student#getId()}
     *   <li>{@link Student#getName()}
     *   <li>{@link Student#getPhoneNumber()}
     *   <li>{@link Student#getSurname()}
     * </ul>
     */
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
        assertEquals("NameDoe", actualStudent.getFullName());
    }


}