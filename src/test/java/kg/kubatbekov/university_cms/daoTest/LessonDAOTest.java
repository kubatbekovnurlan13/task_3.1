package kg.kubatbekov.university_cms.daoTest;

import kg.kubatbekov.university_cms.container.PostgresContainer;
import kg.kubatbekov.university_cms.dao.LessonDAO;
import kg.kubatbekov.university_cms.model.*;
import kg.kubatbekov.university_cms.service.ConsoleApp;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class LessonDAOTest extends PostgresContainer {
    @MockBean
    private ConsoleApp consoleApp;

    @Autowired
    private LessonDAO lessonDAO;


    @Test
    void findAll_testFindAll_whenThereNoValueInput() {
        List<Lesson> cours = lessonDAO.findAll();
        int actual = cours.size();
        Assertions.assertEquals(0, actual);
    }

    @Test
    void findByGroupId_testFindByGroupId_whenThereNoValueInput() {
        List<Lesson> cours = lessonDAO.findByGroupId(1);
        int actual = cours.size();
        Assertions.assertEquals(0, actual);
    }

    @Test
    void findByProfessorId_testFindByProfessorId_whenThereNoValueInput() {
        List<Lesson> cours = lessonDAO.findByProfessorId(1);
        int actual = cours.size();
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

            Lesson lesson = new Lesson(1, group, subject, professor, timeslot, room);
            List<Lesson> newCours = new ArrayList<>();
            newCours.add(lesson);
            lessonDAO.saveAll(newCours);
        }

        @Test
        void findAll_testFindAll_whenThereIsValueInput() {
            List<Lesson> cours = lessonDAO.findAll();
            int actual = cours.size();
            Assertions.assertEquals(1, actual);
        }

        @Test
        void findByGroupId_testFindByGroupId_whenThereIsValueInput() {
            List<Lesson> cours = lessonDAO.findByGroupId(1);
            int actual = cours.size();
            Assertions.assertEquals(1, actual);
        }

        @Test
        void findByProfessorId_testFindByProfessorId_whenThereIsValueInput() {
            List<Lesson> cours = lessonDAO.findByProfessorId(1);
            int actual = cours.size();
            Assertions.assertEquals(1, actual);
        }
    }
}
