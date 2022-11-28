package it.malda.school.controller;

import it.malda.school.controller.model.TeacherDto;
import it.malda.school.entity.Course;
import it.malda.school.entity.Teacher;
import it.malda.school.mapper.TeacherMapper;
import it.malda.school.service.CourseService;
import it.malda.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/teacher")
@RestController
public class TeacherController {
    public TeacherController(TeacherService teacherService, TeacherMapper teacherMapper) {
        this.teacherService = teacherService;
        this.teacherMapper = teacherMapper;
    }
    @Autowired
    private final TeacherService teacherService;

    private final TeacherMapper teacherMapper;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<TeacherDto> getList(@RequestParam(name = "size", defaultValue = "100") int size) throws Exception {
        return teacherMapper.toDto(this.teacherService.getList(size));
    }

    @GetMapping(path = {"/{id}"})
    public TeacherDto getOne(@PathVariable Long id) throws Exception {
        Teacher teacher = this.teacherService.getOne(id);
        if (teacher == null) return null;
        List<Course> course = this.courseService.findCourseListByTeacher(teacher);
        TeacherDto teacherDto = this.teacherMapper.toDto(teacher);
        teacherDto.setCousers(course.stream().map(Course::getName).collect(Collectors.toList()));
        return teacherDto;
    }

    @PostMapping
    public TeacherDto insert(@RequestBody TeacherDto teacher) throws Exception {
        Teacher entity = this.teacherMapper.toEntity(teacher);
        TeacherDto teacherDto = this.teacherMapper.toDto(this.teacherService.insert(entity));
        return teacherDto;
    }

    @DeleteMapping(path = {"/{id}"})
    public String delete(@PathVariable Long id) throws Exception {
        this.teacherService.delete(id);
        return "Deleted Teacher";
    }

    @PutMapping("/{id}")
    public TeacherDto update(@PathVariable Long id, @RequestBody TeacherDto teacher) throws Exception {
        Teacher entity = this.teacherMapper.toEntity(teacher);
        TeacherDto teacherDto = this.teacherMapper.toDto(this.teacherService.update(id, entity));
        return teacherDto;
    }
}
