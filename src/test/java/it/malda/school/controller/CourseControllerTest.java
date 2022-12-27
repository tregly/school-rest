package it.malda.school.controller;

import it.malda.school.controller.model.CourseDto;
import it.malda.school.entity.Course;
import it.malda.school.entity.Student;
import it.malda.school.entity.Teacher;
import it.malda.school.mapper.CourseMapper;
import it.malda.school.service.CourseService;
import it.malda.school.service.StudentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CourseController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CourseControllerTest {

    private MockMvc mvc;

    @MockBean
    private static CourseService courseService;

    @MockBean
    private static StudentService studentService;

    @Autowired
    private CourseController courseController;

    @MockBean
    private CourseMapper courseMapper;

    @Autowired
    private WebApplicationContext context;



    /* @BeforeEach
     void setUp() throws Exception{
         MockitoAnnotations.openMocks(this);

         //client = MockMvcWebTestClient.bindToApplicationContext(this.wac).build();
         Course course = Course.builder()
                 .name("Corso di Geografia")
                 .id(1L)
                 .teacher(Teacher.builder()
                         .id(2L)
                         .build())
                 .maxParticipants(20L)
                 .build();


     }*/
    @BeforeAll
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }


/*    @Test
    void testController() throws Exception {
        when(courseService.getOne(1L)).thenReturn(new Course().toBuilder().id(1L).build());
        mvc.perform(get("api/course/1")).andDo(print()).andExpect(status().isOk());
    }*/

    @Test
    void testGetOne() throws Exception {
        Course course = Course.builder()
                .id(123L)
                .name("Corso di Musica")
                .maxParticipants(25L)
                .teacher(Teacher.builder().id(123L).build())
                .build();
        List<Student> students = new ArrayList<>();
        students.add(Student.builder()
                .id(1L)
                .name("Giuseppe")
                .surname("Maldarelli")
                .coursesRegistration(Set.of(course))
                .build());

        when(studentService.getStudentsByCourse((Course) any())).thenReturn(Set.of(Student.builder().id(1L).build()));
        when(courseService.getOne((Long) any())).thenReturn(course);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/course/1");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print());
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
    }


    @Test
    void testGetInventoryItemFilters() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/api/course");
        MockMvcBuilders.standaloneSetup(this.courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getList() throws Exception{

    }

    @Test
    void getOne() {
    }

    @Test
    void insert() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void assignTeacher() {
    }

    @Test
    void unassignTeacher() {
    }

    @Test
    void assignStudent() {
    }

    @Test
    void unassignStudent() {
    }

    @Test
    void modifyMaxParticipants() {
    }
}
