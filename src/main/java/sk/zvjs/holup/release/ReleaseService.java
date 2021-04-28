package sk.zvjs.holup.release;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.zvjs.holup.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReleaseService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Autowired
    private ReleaseRepository releaseRepository;

    public Optional<Release> fetchReleaseByUserId(UUID uuid) {
        return releaseRepository.findReleaseByUserId(uuid);
    }

    public void createReleaseToUser(User user, String releaseDate) {
        var releaseDateTime = LocalDateTime.parse(releaseDate, FORMATTER);
        var release = new Release(user, releaseDateTime, false);
        releaseRepository.save(release);
    }

    public Release updateReleaseByUserId(UUID uuid, String releaseDate) {
        var release = fetchReleaseByUserId(uuid);
        if (release.isPresent()) {
            var releaseDateTime = LocalDateTime.parse(releaseDate, FORMATTER);
            release.get().setReleaseDate(releaseDateTime);
            return releaseRepository.save(release.get());
        }
        return null;
    }
}
