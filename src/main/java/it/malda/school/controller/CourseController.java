package it.malda.school.controller;
import it.malda.school.entity.Course;
import it.malda.school.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;


@RequestMapping("api/course")
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public Iterator<Course> getList(@RequestParam(name = "size", defaultValue = "100") int size) throws Exception{
        return this.courseService.getList(size);
    }

    @GetMapping(path = {"/{id}"})
    public Course getOne(@PathVariable Long id) throws Exception{
        return this.courseService.getOne(id);
    }

    @PostMapping
    public Course insert(@RequestBody Course course) throws Exception {
        return this.courseService.insert(course);
    }

    @DeleteMapping(path = {"/{id}"})
    public String delete(@PathVariable Long id) throws Exception{
        this.courseService.delete(id);
        return "Deleted Course";
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @RequestBody Course course) throws Exception {
        return this.courseService.update(id, course);
    }
}
