package sk.zvjs.holup.release;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReleaseDTO {
    private UUID uuid;
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "UTC")
    private LocalDate releaseDate;

    public ReleaseDTO(Release release) {
        this(release.getUser().getId(), release.getReleaseDate());
    }
}
