CREATE TABLE courses_students (
   course_id BIGINT NOT NULL,
   student_id BIGINT NOT NULL,
   CONSTRAINT pk_courses_students PRIMARY KEY (course_id, student_id)
);

ALTER TABLE courses_students ADD CONSTRAINT FK_COUSTU_ON_COURSE FOREIGN KEY (course_id) REFERENCES course (id);
ALTER TABLE courses_students ADD CONSTRAINT FK_COUSTU_ON_STUDENT FOREIGN KEY (student_id) REFERENCES student (id);