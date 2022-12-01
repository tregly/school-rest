package it.malda.school.controller;

import it.malda.school.controller.model.CourseDto;
import it.malda.school.entity.Course;
import it.malda.school.entity.Student;
import it.malda.school.mapper.CourseMapper;
import it.malda.school.service.CourseService;
import it.malda.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("api/course")
@RestController
public class CourseController {

    public CourseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    @Autowired
    private final CourseService courseService;

    @Autowired
    private StudentService studentService;

    private final CourseMapper courseMapper;


    @GetMapping
    public List<CourseDto> getList(@RequestParam(name = "size", defaultValue = "100") int size) throws Exception {
        List<Course> courses = this.courseService.getList(size);
        if (courses == null) return null;
        List<CourseDto> courseDtos = this.courseMapper.toDto(courses);
        Long count = 0L;
        courseDtos.forEach(courseDto -> {
            courseDto.setNumberOfParticipants((long) courses.stream()
                    .filter(x ->
                            courseDto.getId() == x.getId()
                    )
                    .findFirst()
                    .get()
                    .getStudentRegistration()
                    .size()
            );
        });

        return courseDtos;
    }

    @GetMapping(path = {"/{id}"})
    public CourseDto getOne(@PathVariable Long id) throws Exception {
        Course course = this.courseService.getOne(id);
        CourseDto courseDto = getCourseDto(course);
        if (courseDto == null) return null;
        return courseDto;
    }

    @PostMapping
    public CourseDto insert(@RequestBody CourseDto course) throws Exception {
        return this.courseMapper.toDto(this.courseService.insert(this.courseMapper.toEntity(course)));
    }

    @DeleteMapping(path = {"/{id}"})
    public String delete(@PathVariable Long id) throws Exception {
        this.courseService.delete(id);
        return "Deleted Course";
    }

    @PutMapping("/{id}")
    public CourseDto update(@PathVariable Long id, @RequestBody CourseDto course) throws Exception {
        return this.courseMapper.toDto(this.courseService.update(id, this.courseMapper.toEntity(course)));
    }

    @PatchMapping("/{id}/assign-teacher/{idTeacher}")
    public CourseDto assignTeacher(@PathVariable Long id, @PathVariable Long idTeacher) throws Exception {
        Course course = this.courseService.assignTeacher(id, idTeacher);
        CourseDto courseDto = getCourseDto(course);
        if (courseDto == null) return null;
        return courseDto;
    }

    @PatchMapping("/{id}/unassign-teacher/{idStudent}")
    public String unassignTeacher(@PathVariable Long id, @PathVariable Long idTeacher) throws Exception {
        this.courseService.unassignTeacher(id, idTeacher);
        return "Deleted Teacher with " + idTeacher + " ID.";
    }

    @PatchMapping("/{id}/assign-student/{idStudent}")
    public CourseDto assignStudent(@PathVariable Long id, @PathVariable Long idStudent) throws Exception {
        Course course = this.courseService.assignStudent(id, idStudent);
        CourseDto courseDto = getCourseDto(course);
        if (courseDto == null) return null;
        return courseDto;
    }

    @PatchMapping("/{id}/unassign-student/{idStudent}")
    public String unassignStudent(@PathVariable Long id, @PathVariable Long idStudent) throws Exception {
        this.courseService.unassignStudent(id, idStudent);
        return "Deleted Student with " + idStudent + " ID.";
    }

    @PatchMapping("/{id}/max-participants/{limit}")
    public String modifyMaxParticipants(@PathVariable Long id, @PathVariable Long limit) throws Exception{
        Long participantsNumber = this.studentService.countStudentByCourse(id);
        if (participantsNumber > limit) {
            throw new Exception("Students registration have already exceeded the limit");
        }
        this.courseService.modifyMaxParticipants(id, limit);
        return "Max Participants is changed to " + limit;
    }


    private CourseDto getCourseDto(Course course) {
        if (course == null) return null;
        CourseDto courseDto = this.courseMapper.toDto(course);
        List<String> student = course.getStudentRegistration().stream().map(Student::getFullName).collect(Collectors.toList());
        courseDto.setParticipants(student);
        return courseDto;
    }


}
