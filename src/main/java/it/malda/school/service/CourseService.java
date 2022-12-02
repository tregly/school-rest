package it.malda.school.service;

import it.malda.school.entity.Course;
import it.malda.school.entity.Student;
import it.malda.school.entity.Teacher;
import it.malda.school.exception.EntityNotFoundException;
import it.malda.school.exception.ForbiddenInputException;
import it.malda.school.exception.InvalidInputException;
import it.malda.school.repo.CourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {
    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final TeacherService teacherService;

    @Autowired
    private final StudentService studentService;

    public CourseService(CourseRepository courseRepository, TeacherService teacherService, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @Transactional
    public Course insert(Course course) throws Exception {
        if (course == null) throw new ForbiddenInputException("Course should not be null!");
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
        Course course = this.courseRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Course not found with id [%d]", id)));
        Set<Student> students = this.studentService.getStudentsByCourseId(course);
        course.setStudentRegistration(students);
        return course;
    }

    @Transactional
    public void delete(Long id) {
        this.courseRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Course not found with id [%d]", id)));
        this.courseRepository.deleteById(id);
    }

    @Transactional
    public Course update(Long id, Course course) {
        Course oldCourse = this.courseRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Course not found with id [%d]", id)));
        if (course.getId()!=null&&!course.getId().equals(id)) {
            throw new InvalidInputException("Context path ID is different from course.id in JSON body");
        }
        if (this.studentService.countStudentByCourse(id) >= course.getMaxParticipants()) {
            throw new InvalidInputException("The maximum number of participants is " + course.getMaxParticipants());
        }
        course.setId(id);
        course.setTeacher(oldCourse.getTeacher());
        this.courseRepository.save(course);
        return this.getOne(id);
    }

    @Transactional
    public List<Course> findCourseListByTeacher(Teacher teacher) {
        return this.courseRepository.findByTeacherId(teacher.getId());
    }

    @Transactional
    public Course assignTeacher(Long id, Long idTeacher) {
        Course course = this.getOne(id);
        Teacher teacher = this.teacherService.getOne(idTeacher);
        course.setTeacher(teacher);
        return this.courseRepository.save(course);
    }

    @Transactional
    public Course unassignedTeacher(Long id) {
        Course course = getOne(id);
        course.setTeacher(null);
        return this.courseRepository.save(course);
    }

    @Transactional
    public void modifyMaxParticipants(Long id, Long limit) {
        this.courseRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Course not found with id [%d]", id)));
        this.courseRepository.modifyMaxParticipants(id, limit);
    }


    @Transactional
    public Course assignStudent(Long id, Long idStudent) {
        Course course = this.getOne(id);
        Student student = this.studentService.getOne(idStudent);
        if (this.studentService.countStudentByCourse(id) >= course.getMaxParticipants()) {
            throw new InvalidInputException("The maximum number of participants is " + course.getMaxParticipants());
        }
        student.getCoursesRegistration().add(course);
        course.getStudentRegistration().add(student);
        return course;
    }

    @Transactional
    public Course unassignedStudent(Long id, Long idStudent) {
        Course course = this.getOne(id);
        Student student = this.studentService.getOne(idStudent);
        course.getStudentRegistration().remove(student);
        student.getCoursesRegistration().remove(course);
        return course;
    }


}
