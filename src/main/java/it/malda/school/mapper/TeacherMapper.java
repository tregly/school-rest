package it.malda.school.mapper;

import it.malda.school.controller.model.TeacherDto;
import it.malda.school.entity.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper extends BaseMapper<TeacherDto, Teacher> {
    TeacherDto toDto(Teacher entity);

    Teacher toEntity(TeacherDto dto);
}
