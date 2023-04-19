package kg.kubatbekov.university_cms.daoTest;

import kg.kubatbekov.university_cms.container.PostgresContainer;
import kg.kubatbekov.university_cms.dao.CourseDAO;
import kg.kubatbekov.university_cms.model.*;
import kg.kubatbekov.university_cms.service.ConsoleApp;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CourseDAOTest extends PostgresContainer {
    @MockBean
    private ConsoleApp consoleApp;

    @Autowired
    private CourseDAO courseDAO;


    @Test
    void findAll_testFindAll_whenThereNoValueInput() {
        List<Course> courses = courseDAO.findAll();
        int actual = courses.size();
        Assertions.assertEquals(0, actual);
    }

    @Test
    void findByGroupId_testFindByGroupId_whenThereNoValueInput() {
        List<Course> courses = courseDAO.findByGroupId(1);
        int actual = courses.size();
        Assertions.assertEquals(0, actual);
    }

    @Test
    void findByProfessorId_testFindByProfessorId_whenThereNoValueInput() {
        List<Course> courses = courseDAO.findByProfessorId(1);
        int actual = courses.size();
        Assertions.assertEquals(0, actual);
    }

    @Nested
    class TestNest {
        @BeforeEach
        public void saveCourse() {
            Room room = new Room(1, "a1", 15);
            Timeslot timeslot = new Timeslot(1, Timeslot.Weekday.wednesday, Timeslot.Duration.fifth_period.getHours());
            Professor professor = new Professor(1, "Mr. Jack");
            Subject subject = new Subject(1, "subject code ", "subject name");
            Group group = new Group(1, "group name ", 1);

            Course course = new Course(1, group, subject, professor, timeslot, room);
            List<Course> newCourses = new ArrayList<>();
            newCourses.add(course);
            courseDAO.saveAll(newCourses);
        }

        @Test
        void findAll_testFindAll_whenThereIsValueInput() {
            List<Course> courses = courseDAO.findAll();
            int actual = courses.size();
            Assertions.assertEquals(1, actual);
        }

        @Test
        void findByGroupId_testFindByGroupId_whenThereIsValueInput() {
            List<Course> courses = courseDAO.findByGroupId(1);
            int actual = courses.size();
            Assertions.assertEquals(1, actual);
        }

        @Test
        void findByProfessorId_testFindByProfessorId_whenThereIsValueInput() {
            List<Course> courses = courseDAO.findByProfessorId(1);
            int actual = courses.size();
            Assertions.assertEquals(1, actual);
        }
    }
}
