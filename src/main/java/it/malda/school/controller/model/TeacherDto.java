package it.malda.school.controller.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link it.malda.school.entity.Teacher} entity
 */
@Getter
@Setter
@Data
public class TeacherDto implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private List<String> cousers;
    private String subject;
}