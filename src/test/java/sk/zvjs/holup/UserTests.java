package sk.zvjs.holup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import sk.zvjs.holup.user.*;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserTests {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    public void createUser() {
        UserController userController = new UserController(userService);
//        User createdUser =  userController.createUser(new User(UUID.randomUUID(), "test@test.com", "heslo"));
//        assertNotNull(createdUser);
//        assertEquals("test@test.com", createdUser.getEmail());
//        assertTrue(passwordEncoder.matches("heslo", createdUser.getPassword()));
    }

    @Test
    public void findUserByEmail() {
        Optional<User> user = userService.findByEmail("test@test.com");
        assertNotNull(user.get());
        assertTrue(passwordEncoder.matches("heslo", user.get().getPassword()));
    }

//    @AfterEach
//    public void removeCreatedUser() {
//        Optional<User> createdUser = userService.findByEmail("test@test.com");
//        createdUser.ifPresent(user -> userRepository.delete(user));
//    }
}
