package sk.zvjs.holup.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.zvjs.holup.calendar_event.CalendarEvent;
import sk.zvjs.holup.calendar_event.CalendarEventService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/v1/users")
    public List<UserResponseDTO> fetchAllUsers() {
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        for (var user : userService.fetchAllUsers()) {
            userResponseDTOS.add(new UserResponseDTO(user));
        }
        return userResponseDTOS;
    }

    @GetMapping("/api/v1/user/{id}")
    public UserResponseDTO fetchUserById(@PathVariable UUID id) {
        Optional<User> user = userService.fetchUserById(id);
        if (user.isPresent()) {
            return new UserResponseDTO(user.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with " + id + " id not found");
    }

    @PostMapping("/api/v1/auth")
    public UserResponseDTO auth(@RequestBody AuthDTO authDTO) {
        return userService.authenticate(authDTO.getEmail(), authDTO.getPassword());
    }

    @PostMapping("/api/v1/register")
    public User createUser(@RequestBody User user) {
        return userService.createNewUser(user);
//        return new UserResponseDTO(user);
    }
}
