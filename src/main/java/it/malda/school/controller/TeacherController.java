package it.malda.school.controller;

import it.malda.school.controller.model.TeacherDto;
import it.malda.school.entity.Course;
import it.malda.school.entity.Teacher;
import it.malda.school.mapper.TeacherMapper;
import it.malda.school.service.CourseService;
import it.malda.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/teacher")
@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    private final TeacherMapper teacherMapper;

    private final CourseService courseService;

    @GetMapping
    public List<TeacherDto> getList(@RequestParam(name = "size", defaultValue = "100") int size){
        return teacherMapper.toDto(this.teacherService.getList(size));
    }

    @GetMapping(path = {"/{id}"})
    public TeacherDto getOne(@PathVariable Long id){
        Teacher teacher = this.teacherService.getOne(id);
        List<Course> course = this.courseService.findCourseListByTeacher(teacher);
        TeacherDto teacherDto = this.teacherMapper.toDto(teacher);
        teacherDto.setCourses(course.stream().map(Course::getName).collect(Collectors.toList()));
        return teacherDto;
    }

    @PostMapping
    public TeacherDto insert(@RequestBody TeacherDto teacher){
        Teacher entity = this.teacherMapper.toEntity(teacher);
        return this.teacherMapper.toDto(this.teacherService.insert(entity));
    }

    @DeleteMapping(path = {"/{id}"})
    public String delete(@PathVariable Long id){
        this.teacherService.delete(id);
        return "Deleted Teacher";
    }

    @PutMapping("/{id}")
    public TeacherDto update(@PathVariable Long id, @RequestBody TeacherDto teacher) {
        Teacher entity = this.teacherMapper.toEntity(teacher);
        List<Course> course = this.courseService.findCourseListByTeacher(entity);
        TeacherDto teacherDto = this.teacherMapper.toDto(this.teacherService.update(id, entity));
        //Nell'update ?? possibile modificare tutto tranne i corsi collegati, ?? possibile farlo tramite chiamate da Course
        teacherDto.setCourses(course.stream().map(Course::getName).collect(Collectors.toList()));
        return teacherDto;
    }
}
