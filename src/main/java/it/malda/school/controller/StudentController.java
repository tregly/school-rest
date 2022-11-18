package it.malda.school.controller;

import it.malda.school.entity.Student;
import it.malda.school.service.StudentService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RequestMapping("api/student")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Iterator<Student> getList(@RequestParam(name = "size", defaultValue = "100") int size) throws Exception{
        return this.studentService.getList(size);
    }

    @GetMapping(path = {"/{id}"})
    public Student getOne(@PathVariable Long id) throws Exception{
        return this.studentService.getOne(id);
    }

    @PostMapping
    public Student insert(@RequestBody Student student) throws Exception {
        return this.studentService.insert(student);
    }

    @DeleteMapping(path = {"/{id}"})
    public String delete(@PathVariable Long id) throws Exception{
        this.studentService.delete(id);
        return "Deleted Student";
    }
}
