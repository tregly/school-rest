package it.malda.school.mapper;

import it.malda.school.controller.model.StudentDto;
import it.malda.school.entity.Course;
import it.malda.school.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface StudentMapper extends BaseMapper<StudentDto, Student> {
    @Mapping(source = "coursesRegistration", target = "courses", qualifiedByName = "getCourse")
    StudentDto toDto(Student entity);

    Student toEntity(StudentDto dto);

    @Named("getCourse")
    default Set<String> getsCourse(Set<Course> courses) {
        if (courses == null) {
            return null;
        }
        Set<String> response = new HashSet<>();
        courses.forEach(course -> {
            response.add(course.getName());
        });
        return response;
    }
}
