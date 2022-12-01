package it.malda.school.service;

import it.malda.school.entity.Course;
import it.malda.school.entity.Student;
import it.malda.school.repo.CourseRepository;
import it.malda.school.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService{
    @Autowired
    private StudentRepository studentRepository;



    @Transactional
    public Student insert(Student student) throws Exception{
        if (student == null){
            throw new Exception("Student should not be null!");
        }else {
            return this.studentRepository.save(student);
        }
    }
    @Transactional
    public List<Student> getList(int size){
        return this.studentRepository.findAll();
    }

    @Transactional
    public Student getOne(Long id){
        return this.studentRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        this.studentRepository.deleteById(id);
    }

    @Transactional
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

    @Transactional
    public Set<Student> getStudentsByCourseId(Course course) {
         return this.studentRepository.findByCoursesRegistrationId(course.getId());
    }

    @Transactional
    public Long countStudentByCourse(Long id) throws Exception {
        return this.studentRepository.countByCoursesRegistrationId(id);
    }


        /*Course course = this.courseService.getOne(id);
        Long participants = this.studentRepository.countByCourseId(id);
        if (course == null) throw new Exception(String.format("Course with ID [%d] is null", id));
        if (participants >= count) {
            throw new Exception("Students registration have already exceeded the limit");
        }else{
            course.setMaxParticipants(count);
        }*/
}
