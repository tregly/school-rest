package it.malda.school.controller.model;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link it.malda.school.entity.Teacher} entity
 */
@Data
public class TeacherDto implements Serializable {
    private final Long id;
    private final String name;
    private final String surname;
    private final String subject;
}