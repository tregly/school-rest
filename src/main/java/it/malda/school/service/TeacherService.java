package it.malda.school.service;

import it.malda.school.entity.Teacher;
import it.malda.school.exception.EntityNotFoundException;
import it.malda.school.exception.InvalidInputException;
import it.malda.school.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Transactional
    public Teacher insert(Teacher teacher) throws Exception {
        if (teacher == null) {
            throw new Exception("Teacher should not be null!");
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
        Optional<Teacher> optional = this.teacherRepository.findById(id);
        if (!optional.isPresent()) return null;
        return optional.get();
    }

    @Transactional
    public void delete(Long id) throws Exception {
        this.teacherRepository.deleteById(id);
    }

    @Transactional
    public Teacher update(Long id, Teacher teacher) throws Exception {
        if (teacher.getId() != null && teacher.getId() != id) {
            throw new InvalidInputException("Context path ID is different from teacher.id in JSON body");
        } else {
            Optional<Teacher> optional = this.teacherRepository.findById(id);
            if (!optional.isPresent()) {
                throw new EntityNotFoundException(String.format("Teacher not found whit id [%d]", id));
            }
        }
        teacher.setId(id);
        return this.teacherRepository.save(teacher);
    }
}
