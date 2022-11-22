package it.malda.school.controller.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link it.malda.school.entity.Course} entity
 */
@Getter
@Setter
@Data
public class CourseDto implements Serializable {

    private Long id;
    private String name;
    private String teacherName;
    private List<String> participants;
    private Long numberOfParticipants;
    private Long maxParticipants;
}
