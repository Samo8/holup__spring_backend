package sk.zvjs.holup.accommodation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AccommodationController {
    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping("/api/v1/download_accommodations")
    public String downloadAccommodations() {
        try {
            accommodationService.saveAccommodationsToDatabase();
            return "Success";
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new ResponseStatusException(HttpStatus.METHOD_FAILURE, "Something went wrong");
        }
    }

    @PostMapping("/api/v1/accommodations")
    public List<Accommodation> fetchAccommodations(@RequestBody AccommodationDTO accommodationDTO) {
        return accommodationService.fetchAccommodations(accommodationDTO);
    }
}
