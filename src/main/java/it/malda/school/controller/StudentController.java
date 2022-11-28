package it.malda.school.controller;

import it.malda.school.controller.model.StudentDto;
import it.malda.school.controller.model.TeacherDto;
import it.malda.school.entity.Course;
import it.malda.school.entity.Student;
import it.malda.school.entity.Teacher;
import it.malda.school.mapper.CourseMapper;
import it.malda.school.mapper.StudentMapper;
import it.malda.school.mapper.TeacherMapper;
import it.malda.school.service.CourseService;
import it.malda.school.service.StudentService;
import it.malda.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping("api/student")
@RestController
public class StudentController {

    public StudentController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<StudentDto> getList(@RequestParam(name = "size", defaultValue = "100") int size) throws Exception{
        return studentMapper.toDto(this.studentService.getList(size));
    }

    @GetMapping(path = {"/{id}"})
    public StudentDto getOne(@PathVariable Long id) throws Exception{
        Student student = this.studentService.getOne(id);
        if (student == null) return null;
        StudentDto studentDto = this.studentMapper.toDto(student);
        return studentDto;
    }

    @PostMapping
    public StudentDto insert(@RequestBody StudentDto student) throws Exception {
        Student entity = this.studentMapper.toEntity(student);
        StudentDto studentDto = this.studentMapper.toDto(this.studentService.insert(entity));
        return studentDto;
    }

    @DeleteMapping(path = {"/{id}"})
    public String delete(@PathVariable Long id) throws Exception{
        this.studentService.delete(id);
        return "Deleted Student";
    }

    @PutMapping("/{id}")
    public StudentDto update(@PathVariable Long id, @RequestBody StudentDto student) throws Exception {
        Student entity = this.studentMapper.toEntity(student);
        StudentDto studentDto = this.studentMapper.toDto(this.studentService.update(id, entity));
        return studentDto;
    }
}
