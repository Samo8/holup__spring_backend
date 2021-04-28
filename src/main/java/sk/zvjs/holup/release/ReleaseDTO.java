package sk.zvjs.holup.release;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReleaseDTO {
    private UUID uuid;
    private LocalDateTime releaseDate;

    public ReleaseDTO(Release release) {
        this(release.getUser().getId(), release.getReleaseDate());
    }
}
