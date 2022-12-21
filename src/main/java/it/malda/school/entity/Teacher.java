package it.malda.school.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder(toBuilder=true)
@NoArgsConstructor
@Entity(name = "teacher")
public class Teacher {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "subject")
    private String subject;

    public String getFullName() {
        return this.getName() + " " + this.getSurname().replace(this.getName(), "");
    }
}
