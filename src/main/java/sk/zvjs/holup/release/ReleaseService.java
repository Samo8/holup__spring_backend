package sk.zvjs.holup.release;

import org.springframework.stereotype.Service;
import sk.zvjs.holup.user.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReleaseService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final ReleaseRepository releaseRepository;

    public ReleaseService(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    public Optional<Release> fetchReleaseByUserId(UUID uuid) {
        return releaseRepository.findReleaseByUserId(uuid);
    }

    public void createReleaseToUser(User user, String releaseDate) {
        var releaseDateTime = LocalDate.parse(releaseDate, FORMATTER);
        var release = new Release(user, releaseDateTime);
        releaseRepository.save(release);
    }

    public Release updateReleaseByUserId(UUID uuid, String releaseDate) {
        var release = fetchReleaseByUserId(uuid);
        if (release.isPresent()) {
            var releaseDateTime = LocalDate.parse(releaseDate, FORMATTER);
            release.get().setReleaseDate(releaseDateTime);
            return releaseRepository.save(release.get());
        }
        return null;
    }
}
