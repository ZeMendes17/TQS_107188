package pt.ua.deti.backend.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    void gettersAndSettersUserTest() {
        User user = new User();
        user.setId(1);
        user.setName("Ze");
        user.setEmail("ze@example.com");

        assertEquals(1, user.getId());
        assertEquals("Ze", user.getName());
        assertEquals("ze@example.com", user.getEmail());

        User user2 = new User("Mendes", "mendes@example.com");

        assertEquals("Mendes", user2.getName());
        assertEquals("mendes@example.com", user2.getEmail());
    }
}
