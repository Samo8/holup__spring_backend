package sk.zvjs.holup.release;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.zvjs.holup.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Release {
    @Id
    private UUID id;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private User user;
    private LocalDate releaseDate;

    public Release(User user, LocalDate releaseDate) {
        this.user = user;
        this.releaseDate = releaseDate;
    }
}
