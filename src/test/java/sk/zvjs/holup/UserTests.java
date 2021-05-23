package sk.zvjs.holup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import sk.zvjs.holup.user.*;

import javax.transaction.Transactional;
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
        var createdUser = userService.createNewUser(new User(UUID.randomUUID(),99999L, "heslo"));
        assertNotNull(createdUser);
        assertEquals(99999L, createdUser.getConvictedNumber());
        assertNotNull(createdUser.getId());
        assertTrue(passwordEncoder.matches("heslo", createdUser.getPassword()));
    }

    @Test
    public void findUserByConvictedNumber() {
        createUser();
        var user = userService.findByConvictedNumber(99999L);
        assertTrue(user.isPresent());
        assertNotNull(user.get());
        assertTrue(passwordEncoder.matches("heslo", user.get().getPassword()));
    }

    @AfterEach
    public void removeCreatedUser() {
        var createdUser = userService.findByConvictedNumber(99999L);
        createdUser.ifPresent(user -> userRepository.delete(user));
    }
}
