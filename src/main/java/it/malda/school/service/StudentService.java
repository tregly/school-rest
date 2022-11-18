package it.malda.school.service;

import it.malda.school.entity.Student;

import java.util.Iterator;

public interface StudentService {

    Student insert(Student student) throws Exception;

    Iterator<Student> getList(int size)  throws Exception;

    Student getOne(Long id) throws Exception;

    void delete(Long id) throws Exception;
}
