package sk.zvjs.holup.release;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.zvjs.holup.user.UserService;
import java.util.UUID;

@RestController
public class ReleaseController {
    @Autowired
    private ReleaseService releaseService;
    @Autowired
    private UserService userService;

    @GetMapping("/release/{userId}")
    public Release fetchUsersRelease(@PathVariable UUID userId) {
        var user = userService.fetchUserById(userId);
        if (user.isPresent()) {
            var release =  releaseService.fetchReleaseByUserId(userId);
            if (release.isPresent()) {
                return release.get();
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No release date for user with " + userId + " id was found");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with " + userId + " id not found");
    }

    @PostMapping("/api/v1/release/{userId}")
    public ResponseEntity<String> createReleaseToUser(@PathVariable UUID userId, @RequestBody ObjectNode jsonNodes) {
        var user = userService.fetchUserById(userId);
        var releaseDate = jsonNodes.get("releaseDate").asText();
        if (user.isPresent() && releaseDate != null) {
            releaseService.createReleaseToUser(user.get(), releaseDate);
            return new ResponseEntity<>(
                    "Created",
                    HttpStatus.CREATED
            );
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with " + userId + " id not found");
    }

    @PatchMapping("/api/v1/release/{userId}")
    public ReleaseDTO updateReleaseByUserId(@PathVariable UUID userId, @RequestBody ObjectNode jsonNodes) {
        var releaseDate = jsonNodes.get("releaseDate").asText();
        if (releaseDate != null) {
            var release = releaseService.updateReleaseByUserId(userId, releaseDate);
            if (release != null) {
                return new ReleaseDTO(release);
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with " + userId + " id not found");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Release date can't be null");
    }
}
