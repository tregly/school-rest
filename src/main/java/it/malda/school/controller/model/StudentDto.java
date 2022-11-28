package it.malda.school.controller.model;

import it.malda.school.entity.Course;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link it.malda.school.entity.Student} entity
 */
@Getter
@Setter
@Data
public class StudentDto implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String age;
    private String phoneNumber;
    private Set<String> courses;
}