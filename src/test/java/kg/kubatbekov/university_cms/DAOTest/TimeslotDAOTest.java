package kg.kubatbekov.university_cms.DAOTest;

import kg.kubatbekov.university_cms.dao.TimeslotDAO;
import kg.kubatbekov.university_cms.service.ConsoleApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TimeslotDAOTest {
    @MockBean
    private ConsoleApp consoleApp;
    @Autowired
    private TimeslotDAO timeslotDAO;

    @Test
    void getAll_testGetAllValues_whenThereIsValues() {
        int actual = timeslotDAO.findAll().size();
        Assertions.assertEquals(25, actual);
    }

}
