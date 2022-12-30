package it.malda.school.repo;

import it.malda.school.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByTeacherId(Long id);

    @Modifying
    @Query("update Course c set c.maxParticipants = :limit where c.id = :id")
    int modifyMaxParticipants(@Param("id") Long id, @Param("limit") Long limit);

}
