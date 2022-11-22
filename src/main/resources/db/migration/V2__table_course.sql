CREATE TABLE course (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NULL,
   teacher_id BIGINT NULL,
   CONSTRAINT pk_course PRIMARY KEY (id)
);

ALTER TABLE course ADD CONSTRAINT FK_COURSE_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teacher (id);