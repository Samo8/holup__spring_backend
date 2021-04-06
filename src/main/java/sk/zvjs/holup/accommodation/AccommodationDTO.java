package sk.zvjs.holup.accommodation;

import lombok.*;
import sk.zvjs.holup.address.Location;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccommodationDTO {
    private List<String> type = new ArrayList<>();
    private String age;
    private String gender;
    private String region;
    private Double distance;
    private Location location;
}
