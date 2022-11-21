package it.malda.school.service;

import it.malda.school.entity.Course;
import it.malda.school.entity.Student;
import it.malda.school.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService{
    @Autowired
    private StudentRepository studentRepository;


    public Student insert(Student student) throws Exception{
        if (student == null){
            throw new Exception("Student should not be null!");
        }else {
            return this.studentRepository.save(student);
        }
    }

    public Iterator<Student> getList(int size){
        return this.studentRepository.findAll().iterator();
    }

    public Student getOne(Long id){
        return this.studentRepository.findById(id).orElse(null);
    }


    public void delete(Long id) throws Exception {
        this.studentRepository.deleteById(id);
    }

    public Student update(Long id, Student student) throws Exception{
        if(student.getId() != null && student.getId() != id){
            throw new Exception("Context path ID is different from student.id in JSON body");
        }else {
            Optional<Student> optional = this.studentRepository.findById(id);
            if (!optional.isPresent()){
                throw new Exception("No student found with ID " + id);
            }
        }
        student.setId(id);
        return this.studentRepository.save(student);
    }

    public Set<Student> getStudentsByCourseId(Course course) {
         return this.studentRepository.findByCoursesRegistrationId(course.getId());
    }
}
