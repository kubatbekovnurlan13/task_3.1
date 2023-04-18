package kg.kubatbekov.university_cms.DAOTest;

import kg.kubatbekov.university_cms.dao.ProfessorDAO;
import kg.kubatbekov.university_cms.model.Professor;
import kg.kubatbekov.university_cms.service.ConsoleApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class ProfessorDAOTest {
    @MockBean
    private ConsoleApp consoleApp;
    @Autowired
    private ProfessorDAO professorDAO;

    @Test
    void getAll_testGetAllValues_whenThereIsValues() {
        int actual = professorDAO.getAll().size();
        Assertions.assertEquals(5, actual);
    }

    @Test
    void findById_testFindById_whenThereIsValues() {
        Optional<Professor> actual = professorDAO.findById(1);
        Assertions.assertNotNull(actual);
    }

}
