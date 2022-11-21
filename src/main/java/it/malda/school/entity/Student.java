package it.malda.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private String age;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "courses_students",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> coursesRegistration;
}
