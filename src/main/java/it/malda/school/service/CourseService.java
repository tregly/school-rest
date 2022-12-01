package it.malda.school.service;

import it.malda.school.entity.Course;
import it.malda.school.entity.Student;
import it.malda.school.entity.Teacher;
import it.malda.school.repo.CourseRepository;
import it.malda.school.repo.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public Course insert(Course course) throws Exception {
        if (course == null) {
            throw new Exception("Course should not be null!");
        }
        return this.courseRepository.save(course);
    }

    @Transactional
    public List<Course> getList(int size) {
        List<Course> courses = this.courseRepository.findAll();
        List<Course> coursesResp = new ArrayList<>();
        courses.forEach(x -> {
            Course course = new Course();
            BeanUtils.copyProperties(x, course);
            Set<Student> students = this.studentService.getStudentsByCourseId(x);
            course.setStudentRegistration(students);
            coursesResp.add(course);
        });
        return coursesResp;
    }

    @Transactional
    public Course getOne(Long id) {
        Optional<Course> optional = this.courseRepository.findById(id);
        if (!optional.isPresent()) return null;
        Course course = optional.get();
        Set<Student> students = this.studentService.getStudentsByCourseId(course);
        course.setStudentRegistration(students);
        return course;
    }

    @Transactional
    public void delete(Long id) throws Exception {
        this.courseRepository.deleteById(id);
    }

    @Transactional
    public Course update(Long id, Course course) throws Exception {
        if (course.getId() != null && course.getId() != id) {
            throw new Exception("Context path ID is different from course.id in JSON body");
        } else {
            Optional<Course> optional = this.courseRepository.findById(id);
            if (!optional.isPresent()) {
                throw new Exception("No teacher found with ID " + id);
            }
        }
        if (course.getStudentRegistration().size() > course.getMaxParticipants()){
            throw new Exception("The maximum number of participants is " + course.getMaxParticipants());
        }
        course.setId(id);
        return this.courseRepository.save(course);
    }

    @Transactional
    public List<Course> findCourseListByTeacher(Teacher teacher) {
        return this.courseRepository.findByTeacherId(teacher.getId());
    }

    @Transactional
    public Course assignTeacher(Long id, Long idTeacher) throws Exception {
        Course course = this.getOne(id);
        if (course == null) throw new Exception(String.format("Course with ID [%d]", id));
        Teacher teacher = this.teacherService.getOne(idTeacher);
        if (teacher == null) throw new Exception(String.format("Teacher with ID [%d]", idTeacher));
        course.setTeacher(teacher);
        return this.courseRepository.save(course);
    }

    @Transactional
    public void unassignTeacher(Long id, Long idTeacher) throws Exception {
        Course course = this.getOne(id);
        if (course == null) throw new Exception(String.format("Course with ID [%d]", id));
        Teacher teacher = this.teacherService.getOne(idTeacher);
        if (teacher == null) throw new Exception(String.format("Student with ID [%d]", idTeacher));
        course.getStudentRegistration().remove(teacher);
    }

    @Transactional
    public void modifyMaxParticipants(Long id, Long limit) {
        this.courseRepository.modifyMaxParticipants(id,limit);
    }


    @Transactional
    public Course assignStudent(Long id, Long idStudent) throws Exception {
        Course course = this.getOne(id);
        if (course == null) throw new Exception(String.format("Course with ID [%d]", id));
        Student student = this.studentService.getOne(idStudent);
        if (course.getStudentRegistration().size() >= course.getMaxParticipants()){
            throw new Exception("the maximum number of participants is " + course.getMaxParticipants());
        }
        if (student == null) throw new Exception(String.format("Student with ID [%d]", idStudent));
        student.getCoursesRegistration().add(course);
        course.getStudentRegistration().add(student);
        return course;
    }
    @Transactional
    public void unassignStudent(Long id, Long idStudent) throws Exception {
        Course course = this.getOne(id);
        if (course == null) throw new Exception(String.format("Course with ID [%d]", id));
        Student student = this.studentService.getOne(idStudent);
        if (student == null) throw new Exception(String.format("Student with ID [%d]", idStudent));
        course.getStudentRegistration().remove(student);
        student.getCoursesRegistration().remove(course);
    }



}
