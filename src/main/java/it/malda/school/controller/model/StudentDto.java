package it.malda.school.controller.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link it.malda.school.entity.Student} entity
 */
@Getter
@Setter
@Data
@SuperBuilder
@NoArgsConstructor
public class StudentDto implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String age;
    private String phoneNumber;
    private Set<String> courses;
}