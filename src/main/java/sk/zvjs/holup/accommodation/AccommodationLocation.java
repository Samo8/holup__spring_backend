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
public class AccommodationLocation {
    public Double lat;
    public Double lon;
}
