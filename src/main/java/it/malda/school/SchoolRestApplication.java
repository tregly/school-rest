package it.malda.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages =  {"it.malda.school"})
public class SchoolRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolRestApplication.class, args);
    }

}
