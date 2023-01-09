package it.malda.school.controller;

import it.malda.school.controller.model.StudentDto;
import it.malda.school.entity.Student;
import it.malda.school.mapper.StudentMapper;
import it.malda.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/student")
@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    private final StudentMapper studentMapper;

    @GetMapping
    public List<StudentDto> getList(@RequestParam(name = "size", defaultValue = "100") int size){
        return studentMapper.toDto(this.studentService.getList(size));
    }

    @GetMapping(path = {"/{id}"})
    public StudentDto getOne(@PathVariable Long id){
        Student student = this.studentService.getOne(id);
        return this.studentMapper.toDto(student);
    }

    @PostMapping
    public StudentDto insert(@RequestBody StudentDto student){
        Student entity = this.studentMapper.toEntity(student);
        return this.studentMapper.toDto(this.studentService.insert(entity));
    }

    @DeleteMapping(path = {"/{id}"})
    public String delete(@PathVariable Long id){
        this.studentService.delete(id);
        return "Deleted Student";
    }

    @PutMapping("/{id}")
    public StudentDto update(@PathVariable Long id, @RequestBody StudentDto student) {
        Student entity = this.studentMapper.toEntity(student);
        return this.studentMapper.toDto(this.studentService.update(id, entity));
    }
}
