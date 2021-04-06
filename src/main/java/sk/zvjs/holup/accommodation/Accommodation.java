package sk.zvjs.holup.accommodation;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Accommodation {
    @Id
    public Long id;
    public String name;
    @Column(length = 64)
    public String address;
    @Column(length = 64)
    public String phoneNumber;
    @Column(length = 64)
    public String email;
    @Column(length = 64)
    public String webPage;
    @Column(length = 16)
    public String gender;
    public String age;
    @Column(length = 64)
    public String type;
    public Double lat;
    public Double lon;
    @Column(length = 64)
    public String region;
    @Column(length = 64)
    public String district;
    @Column(length = 64)
    public String street;
    @Column(length = 8)
    public String postCode;
}
