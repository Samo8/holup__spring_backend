package sk.zvjs.holup.accommodation;

import lombok.*;
import sk.zvjs.holup.address.Location;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccommodationDTO {
    private Set<String> types;
    private String gender;
    private Set<String> regions;
    private Set<String> districts;
    private Double distance;
    private Location location;
}
