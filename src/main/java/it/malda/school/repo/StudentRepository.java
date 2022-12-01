package it.malda.school.repo;

import it.malda.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
/*    @Query("SELECT s FROM Student s where s.coursesRegistration = :course")
    Set<Student> getStudentsByCourseId(Course course);*/

    Set<Student> findByCoursesRegistrationId(Long id);

    Long countByCoursesRegistrationId(Long id);

}
