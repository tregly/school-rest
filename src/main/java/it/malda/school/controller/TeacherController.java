package it.malda.school.controller;

import it.malda.school.entity.Teacher;
import it.malda.school.repo.TeacherRepository;
import it.malda.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RequestMapping("api/teacher")
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public Iterator<Teacher> getList(@RequestParam(name = "size", defaultValue = "100") int size) throws Exception{
        return this.teacherService.getList(size);
    }

    @GetMapping(path = {"/{id}"})
    public Teacher getOne(@PathVariable Long id) throws Exception{
        return this.teacherService.getOne(id);
    }

    @PostMapping
    public Teacher insert(@RequestBody Teacher teacher) throws Exception {
        return this.teacherService.insert(teacher);
    }

    @DeleteMapping(path = {"/{id}"})
    public String delete(@PathVariable Long id) throws Exception{
        this.teacherService.delete(id);
        return "Deleted Teacher";
    }

    @PutMapping("/{id}")
    public Teacher update(@PathVariable Long id, @RequestBody Teacher teacher) throws Exception {
        return this.teacherService.update(id, teacher);
    }
}
