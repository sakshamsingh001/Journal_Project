package Journal.example.Journal.project.service;

import Journal.example.Journal.project.Entity.User;
import Journal.example.Journal.project.Repository.User_repo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class UserService
{
    @Autowired
    private User_repo ur;

    @ParameterizedTest
    @ValueSource(strings = {
            "ram",
            "shyam",
            "shiva"
    })
    public void testfindbyUsername(String username){
        User user=ur.findByUsername(username);
        assertTrue(!user.getJournalEntries().isEmpty());
    }
    @ParameterizedTest
    @CsvSource({
            "2,1,1",
            "4,2,2",
            "8,2,2"
    })
    public void test(int a,int b,int c)
    {
        assertEquals(a,b+c);
    }




}
