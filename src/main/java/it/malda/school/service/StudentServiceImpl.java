package it.malda.school.service;

import it.malda.school.entity.Student;
import it.malda.school.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Iterator;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;


    public Student insert(Student student) throws Exception{
        if (student == null){
            throw new Exception("Student should not be null!");
        }else {
            return this.studentRepository.save(student);
        }
    }

    public Iterator<Student> getList(int size){

        return this.studentRepository.findAll().iterator();
    }

    public Student getOne(Long id){
        return this.studentRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) throws Exception {
       this.studentRepository.deleteById(id);
    }
}
