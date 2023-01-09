package it.malda.school.service;

import it.malda.school.entity.Teacher;
import it.malda.school.exception.EntityNotFoundException;
import it.malda.school.exception.InvalidInputException;
import it.malda.school.repo.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Transactional
    public Teacher insert(Teacher teacher){
        if (Arrays.asList(null, "").contains(teacher.getName()) || Arrays.asList(null, "").contains(teacher.getSurname())) {
            throw new InvalidInputException("Teacher should not be null!");
        } else {
            return this.teacherRepository.save(teacher);
        }
    }

    @Transactional
    public List<Teacher> getList(int size) {
        return this.teacherRepository.findAll();
    }

    @Transactional
    public Teacher getOne(Long id) {
        return this.teacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Teacher not found with id [%d]", id)));
    }

    @Transactional
    public void delete(Long id){
        this.teacherRepository.deleteById(id);
    }

    @Transactional
    public Teacher update(Long id, Teacher teacher) {
        if (teacher.getId() != null && !teacher.getId().equals(id)) {
            throw new InvalidInputException("Context path ID is different from teacher.id in JSON body");
        } else {
            getOne(id);
        }
        teacher.setId(id);
        return this.teacherRepository.save(teacher);
    }
}
