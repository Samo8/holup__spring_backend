package sk.zvjs.holup.accommodation;

import lombok.*;
import sk.zvjs.holup.address.convert.Location;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccommodationDTO {
    private Set<String> types;
    private Set<String> ages;
    private String gender;
    private Set<String> regions;
    private Set<String> districts;
    private Double distance;
    private Location location;
}
