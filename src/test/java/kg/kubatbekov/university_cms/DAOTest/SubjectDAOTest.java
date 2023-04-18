package kg.kubatbekov.university_cms.DAOTest;

import kg.kubatbekov.university_cms.dao.SubjectDAO;
import kg.kubatbekov.university_cms.service.ConsoleApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class SubjectDAOTest {
    @MockBean
    private ConsoleApp consoleApp;
    @Autowired
    private SubjectDAO subjectDAO;

    @Test
    void getAll_testGetAllValues_whenThereIsValues() {
        int actual = subjectDAO.getAll().size();
        Assertions.assertEquals(6, actual);
    }

    @Test
    void groupsSubjectsSize_testGroupsSubjectsSize_whenThereIsValues() {
        int actual = subjectDAO.subjectsProfessorsSize();
        Assertions.assertEquals(13, actual);
    }

}
