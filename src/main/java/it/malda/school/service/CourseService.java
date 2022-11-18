package it.malda.school.service;

import it.malda.school.entity.Course;
import it.malda.school.entity.Teacher;
import it.malda.school.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Iterator;
import java.util.Optional;

@Service
public class CourseService {
        @Autowired
        private CourseRepository courseRepository;
        private TeacherService teacherService;

        public Course insert(Course course, Long teacher) throws Exception{
            if (course == null){
                throw new Exception("Course should not be null!");
            }else {
                if (course.getTeacher().equals("null"))
                    throw new Exception("It is not possible not to include a teacher");
            }
            Teacher teacherCourse = this.teacherService.getOne(teacher);
            course.setTeacher(teacherCourse);
            return this.courseRepository.save(course);
        }

        public Iterator<Course> getList(int size){
            return this.courseRepository.findAll().iterator();
        }

        public Course getOne(Long id){
            return this.courseRepository.findById(id).orElse(null);
        }

        public void delete(Long id) throws Exception {
            this.courseRepository.deleteById(id);
        }

        public Course update(Long id, Course course) throws Exception{
            if(course.getId() != null && course.getId() != id){
                throw new Exception("Context path ID is different from course.id in JSON body");
            }else{
                Optional<Course> optional = this.courseRepository.findById(id);
                if (!optional.isPresent()){
                    throw new Exception("No teacher found with ID " + id);
                }
            }
            course.setId(id);
            return this.courseRepository.save(course);
        }
}
