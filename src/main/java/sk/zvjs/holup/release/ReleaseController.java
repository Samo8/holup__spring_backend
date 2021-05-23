package sk.zvjs.holup.release;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.zvjs.holup.user.UserService;

import java.util.UUID;

@RestController
public class ReleaseController {
    private final ReleaseService releaseService;
    private final UserService userService;

    public ReleaseController(ReleaseService releaseService, UserService userService) {
        this.releaseService = releaseService;
        this.userService = userService;
    }

    @GetMapping("/api/v1/release/{userId}")
    public ReleaseDTO fetchUsersRelease(@PathVariable UUID userId) {
        var user = userService.fetchUserById(userId);
        if (user.isPresent()) {
            var release = releaseService.fetchReleaseByUserId(userId);
            if (release.isPresent()) {
                return new ReleaseDTO(release.get());
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dátum preupstenia pre pužívateľa s id: " + userId + " nebol nájdený");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Používateľ s číslom " + userId + " nebol nájdený");
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

    @PutMapping("/api/v1/release/{userId}")
    public ReleaseDTO updateReleaseByUserId(@PathVariable UUID userId, @RequestBody ObjectNode jsonNodes) {
        var releaseDate = jsonNodes.get("releaseDate").asText();
        if (releaseDate != null) {
            var release = releaseService.updateReleaseByUserId(userId, releaseDate);
            if (release != null) {
                return new ReleaseDTO(release);
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Používateľ s číslom " + userId + " nebol nájdený");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dátum prepustenia nebol nájdený");
    }
}
