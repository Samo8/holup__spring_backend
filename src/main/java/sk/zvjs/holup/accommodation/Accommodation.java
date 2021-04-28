package sk.zvjs.holup.accommodation;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Accommodation {
    @Id
    private Integer id;
    private String name;
    @Column(length = 64)
    private String phoneNumber;
    @Column(length = 64)
    private String email;
    @Column(length = 64)
    private String webPage;
    @Column(length = 16)
    private String gender;
    private String age;
    @Column(length = 64)
    private String type;
    @Embedded
    private AccommodationLocation location;
    @Embedded
    private AccommodationAddress address;
}
