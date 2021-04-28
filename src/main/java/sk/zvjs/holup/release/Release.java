package sk.zvjs.holup.release;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.zvjs.holup.user.User;

import javax.persistence.*;
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
    private LocalDateTime releaseDate;
    private boolean imported;

    public Release(User user, LocalDateTime releaseDate, boolean imported) {
        this.user = user;
        this.releaseDate = releaseDate;
        this.imported = imported;
    }
}
