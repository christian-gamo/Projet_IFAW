import java.util.Base64;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import persistantData.dao.UserDAO;
import persistantData.users.User;

/**
 * JUnit test to test the UserDAO class
 *
 * @author Darlène
 *
 */
public class UserDAOTest {
    private static UserDAO userDAO;
    private static User user;

    @BeforeAll
    @Order(1)
    public static void setUp() {
        userDAO = new UserDAO();
        user = new User("john.smith@gmail.com", "password", "Smith", "John");

    }

    @Test
    @Order(2)
    public void testInsertAndGet() {
        int userId = userDAO.insert(user);
        Assertions.assertNotEquals(0, userId);

        User retrievedUser = userDAO.get(userId);
        Assertions.assertNotNull(retrievedUser);
        Assertions.assertEquals(user.getEmail(), retrievedUser.getEmail());
        Assertions.assertEquals(user.getPassword(), new String(Base64.getDecoder().decode(retrievedUser.getPassword())));
        Assertions.assertEquals(user.getLastName(), retrievedUser.getLastName());
        Assertions.assertEquals(user.getFirstName(), retrievedUser.getFirstName());
    }

    @Test
    @Order(3)
    public void testGetAll() {
        List<User> users = userDAO.getAll();
        Assertions.assertNotNull(users);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    @Order(4)
    public void testGetByLogin() {
    	int userId = userDAO.getByLogin(user.getEmail(), user.getPassword()).getID_U();
    	Assertions.assertNotEquals(0, userId);
        User retrievedUser = userDAO.get(userId);
        Assertions.assertNotNull(retrievedUser);
    }

    @Test
    public void testDelete() {

        int userId = userDAO.getByLogin(user.getEmail(), user.getPassword()).getID_U();
        Assertions.assertNotEquals(0, userId);
        boolean deletionResult = userDAO.delete(userId);
        Assertions.assertTrue(deletionResult);
        User deletedUser = userDAO.get(userId);
        Assertions.assertNull(deletedUser);
    }
}