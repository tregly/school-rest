package it.malda.school.service;

import it.malda.school.entity.Teacher;
import it.malda.school.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Iterator;
import java.util.Optional;

@Service
public class TeacherService{
    @Autowired
    private TeacherRepository teacherRepository;


    public Teacher insert(Teacher teacher) throws Exception{
        if (teacher == null){
            throw new Exception("Teacher should not be null!");
        }else {
            return this.teacherRepository.save(teacher);
        }
    }

    public Iterator<Teacher> getList(int size){
        return this.teacherRepository.findAll().iterator();
    }

    public Teacher getOne(Long id){
        return this.teacherRepository.findById(id).orElse(null);
    }

    public void delete(Long id) throws Exception {
       this.teacherRepository.deleteById(id);
    }

    public Teacher update(Long id, Teacher teacher) throws Exception{
        if(teacher.getId() != null && teacher.getId() != id){
            throw new Exception("Context path ID is different from teacher.id in JSON body");
        }else {
            Optional<Teacher> optional = this.teacherRepository.findById(id);
            if (!optional.isPresent()){
                throw new Exception("No teacher found with ID " + id);
            }
        }
        teacher.setId(id);
        return this.teacherRepository.save(teacher);
    }
}
