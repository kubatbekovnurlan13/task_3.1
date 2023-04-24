package kg.kubatbekov.university_cms.daoTest;

import kg.kubatbekov.university_cms.dao.StudentDAO;
import kg.kubatbekov.university_cms.model.Student;
import kg.kubatbekov.university_cms.service.ConsoleApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class StudentDAOTest {
    @MockBean
    private ConsoleApp consoleApp;
    @Autowired
    private StudentDAO studentDAO;

    @Test
    void findById_testFindById_whenThereIsValues() {
        Optional<Student> actual = studentDAO.findById(1);
        Assertions.assertNotNull(actual);
    }

}
