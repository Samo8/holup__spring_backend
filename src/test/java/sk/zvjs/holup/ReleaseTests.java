package sk.zvjs.holup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sk.zvjs.holup.release.ReleaseRepository;
import sk.zvjs.holup.release.ReleaseService;
import sk.zvjs.holup.user.User;
import sk.zvjs.holup.user.UserRepository;
import sk.zvjs.holup.user.UserService;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReleaseTests {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReleaseService releaseService;

    @Autowired
    ReleaseRepository releaseRepository;

    @Test
    @Transactional
    public void createReleaseToUser() {
        var createdUser = userService.createNewUser(new User(UUID.randomUUID(),99999L, "heslo"));
        releaseService.createReleaseToUser(createdUser, "10.09.2021");
        var release = releaseService.fetchReleaseByUserId(createdUser.getId());
        assertTrue(release.isPresent());
        assertEquals(release.get().getReleaseDate().format(FORMATTER), "10.09.2021");
    }

    @Test
    @Transactional
    public void getReleaseFromUserWithoutRelease() {
        var createdUser = userService.createNewUser(new User(UUID.randomUUID(),99999L, "heslo"));
        var release = releaseService.fetchReleaseByUserId(createdUser.getId());
        assertFalse(release.isPresent());
    }

    @AfterEach
    public void removeCreatedUserAndRelease() {
        var createdUser = userService.findByConvictedNumber(99999L);
        createdUser.ifPresent(user -> {
            userRepository.delete(user);
            var usersRelease = releaseService.fetchReleaseByUserId(createdUser.get().getId());
            usersRelease.ifPresent(release -> releaseRepository.delete(release));
        });
    }
}
