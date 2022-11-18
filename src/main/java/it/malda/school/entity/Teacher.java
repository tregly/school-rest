package it.malda.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "teacher")
public class Teacher {

    @Id
    @Column(name = "id")
    Long id;
    String name;
    String surname;
    String subject;

}
