package kg.kubatbekov.university_cms.DAOTest;

import kg.kubatbekov.university_cms.container.PostgresContainer;
import kg.kubatbekov.university_cms.dao.GroupDAO;
import kg.kubatbekov.university_cms.service.ConsoleApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class GroupDAOTest extends PostgresContainer {
    @MockBean
    private ConsoleApp consoleApp;
    @Autowired
    private GroupDAO groupDAO;

    @Test
    void getAll_testGetAllValues_whenThereIsValues() {
        int actual = groupDAO.getAll().size();
        Assertions.assertEquals(5, actual);
    }

    @Test
    void groupsSubjectsSize_testGroupsSubjectsSize_whenThereIsValues() {
        int actual = groupDAO.groupsSubjectsSize();
        Assertions.assertEquals(26, actual);
    }

}
