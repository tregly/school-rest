package it.malda.school.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@SuperBuilder(toBuilder=true)
@NoArgsConstructor
@Entity(name = "course")
public class Course {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = true)
    private Teacher teacher;

    @Transient
    private Set<Student> studentRegistration;

    @Column(name = "max_participants", columnDefinition = "default 25")
    private Long maxParticipants;
}
