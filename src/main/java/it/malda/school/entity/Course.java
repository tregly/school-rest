package it.malda.school.entity;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "name")
    private Teacher teacher;

}
