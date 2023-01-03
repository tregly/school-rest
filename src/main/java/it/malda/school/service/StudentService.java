package it.malda.school.service;

import it.malda.school.entity.Course;
import it.malda.school.entity.Student;
import it.malda.school.exception.EntityNotFoundException;
import it.malda.school.exception.InvalidInputException;
import it.malda.school.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;


    @Transactional
    public Student insert(Student student) throws Exception {
        if (Arrays.asList(null, "").contains(student.getName()) || Arrays.asList(null, "").contains(student.getSurname())) {
            throw new InvalidInputException("Student should not be null!");
        } else {
            return this.studentRepository.save(student);
        }
    }

    @Transactional
    public List<Student> getList(int size) {
        return this.studentRepository.findAll();
    }

    @Transactional
    public Student getOne(Long id) {
        return this.studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Student not found with id [%d]", id)));
    }

    @Transactional
    public void delete(Long id) throws Exception {
        this.studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Student not found with id [%d]", id)));
        this.studentRepository.deleteById(id);
    }

    @Transactional
    public Student update(Long id, Student student) {
        if (student.getId() != null && !student.getId().equals(id)) {
            throw new InvalidInputException("Context path ID is different from student.id in JSON body");
        } else {
            getOne(id);
        }
        student.setId(id);
        Set<Course> courses = this.studentRepository.findById(id).get().getCoursesRegistration();
        student.setCoursesRegistration(courses);
        //l'update è possibile farlo su tutto tranne sui corsi iscritti, per quello bisogna fare una chiamata su Course
        return this.studentRepository.save(student);
    }

    @Transactional
    public Set<Student> getStudentsByCourse(Course course) {
        return this.studentRepository.findByCoursesRegistrationId(course.getId());
    }

    @Transactional
    public Long countStudentByCourse(Long id) {
        return this.studentRepository.countByCoursesRegistrationId(id);
    }
}
