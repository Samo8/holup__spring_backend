package sk.zvjs.holup.accommodation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccommodationAddress {
    private String region;
    private String district;
    private String city;
    private String street;
    private String postCode;
}
