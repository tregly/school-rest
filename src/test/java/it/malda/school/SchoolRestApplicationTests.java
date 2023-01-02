package it.malda.school;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class SchoolRestApplicationTests {
    @Autowired
    private JdbcTemplate template;

    @Test
    void contextLoads() {
        assertThat(this.template.queryForObject("SELECT 'Test'",
                String.class)).isEqualTo("Test");
    }
}
