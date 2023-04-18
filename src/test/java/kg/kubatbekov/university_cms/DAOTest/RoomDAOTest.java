package kg.kubatbekov.university_cms.DAOTest;

import kg.kubatbekov.university_cms.dao.RoomDAO;
import kg.kubatbekov.university_cms.service.ConsoleApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class RoomDAOTest {
    @MockBean
    private ConsoleApp consoleApp;
    @Autowired
    private RoomDAO roomDAO;

    @Test
    void getAll_testGetAllValues_whenThereIsValues() {
        int actual = roomDAO.getAll().size();
        Assertions.assertEquals(4, actual);
    }

}
