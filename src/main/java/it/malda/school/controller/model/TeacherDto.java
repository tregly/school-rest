package it.malda.school.controller.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link it.malda.school.entity.Teacher} entity
 */
@Getter
@Setter
@Data
@SuperBuilder
@NoArgsConstructor
public class TeacherDto implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private List<String> courses;
    private String subject;
}