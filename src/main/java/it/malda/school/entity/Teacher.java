package it.malda.school.entity;

import javax.persistence.*;

@Entity(name = "teacher")
public class Teacher {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
    @Column(name = "subject")
    String subject;

}
