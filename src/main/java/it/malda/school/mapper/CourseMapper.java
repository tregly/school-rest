package it.malda.school.mapper;

import it.malda.school.controller.model.CourseDto;
import it.malda.school.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper extends BaseMapper<CourseDto, Course> {
    @Mapping(source = "teacher.fullName", target = "teacherName")
    CourseDto toDto(Course entity);

    Course toEntity(CourseDto dto);
}
