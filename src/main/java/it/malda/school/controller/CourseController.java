package it.malda.school.controller;

import it.malda.school.controller.model.CourseDto;
import it.malda.school.entity.Course;
import it.malda.school.entity.Student;
import it.malda.school.exception.InvalidInputException;
import it.malda.school.mapper.CourseMapper;
import it.malda.school.service.CourseService;
import it.malda.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("api/course")
@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    private final StudentService studentService;

    private final CourseMapper courseMapper;

    @GetMapping
    public List<CourseDto> getList(@RequestParam(name = "size", defaultValue = "100") int size) throws Exception {
        List<Course> courses = this.courseService.getList(size);
        if (courses == null) return null;
        List<CourseDto> courseDtos = this.courseMapper.toDto(courses);
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
        return getCourseDto(this.courseService.getOne(id));
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
    public CourseDto update(@PathVariable Long id, @RequestBody CourseDto course) {
        return getCourseDto(this.courseService.update(id, this.courseMapper.toEntity(course)));
    }

    @PatchMapping("/{id}/assign-teacher/{idTeacher}")
    public CourseDto assignTeacher(@PathVariable Long id, @PathVariable Long idTeacher) {
        return getCourseDto(this.courseService.assignTeacher(id, idTeacher));
    }

    @PatchMapping("/{id}/unassign-teacher")
    public CourseDto unassignTeacher(@PathVariable Long id) {
        return getCourseDto(this.courseService.unassignedTeacher(id));
    }

    @PatchMapping("/{id}/assign-student/{idStudent}")
    public CourseDto assignStudent(@PathVariable Long id, @PathVariable Long idStudent) {
        return getCourseDto(this.courseService.assignStudent(id, idStudent));
    }

    @PatchMapping("/{id}/unassign-student/{idStudent}")
    public CourseDto unassignStudent(@PathVariable Long id, @PathVariable Long idStudent) {
        return getCourseDto(this.courseService.unassignedStudent(id, idStudent));
    }


    @PatchMapping("/{id}/max-participants/{limit}")
    public String modifyMaxParticipants(@PathVariable Long id, @PathVariable Long limit) {
        Long participantsNumber = this.studentService.countStudentByCourse(id);
        if (participantsNumber > limit) {
            throw new InvalidInputException("Students registration have already exceeded the limit");
        }
        this.courseService.modifyMaxParticipants(id, limit);
        return "Max Participants is changed to " + limit;
    }


    private CourseDto getCourseDto(Course course) {
        if (course == null) return null;
        CourseDto courseDto = this.courseMapper.toDto(course);
        List<String> student = course.getStudentRegistration().stream().map(Student::getFullName).collect(Collectors.toList());
        courseDto.setParticipants(student);
        courseDto.setNumberOfParticipants((long) student.size());
        return courseDto;
    }


}
