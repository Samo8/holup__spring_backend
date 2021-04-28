package sk.zvjs.holup;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sk.zvjs.holup.accommodation.Accommodation;
import sk.zvjs.holup.accommodation.AccommodationController;
import sk.zvjs.holup.accommodation.AccommodationDTO;
import sk.zvjs.holup.accommodation.AccommodationService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AccommodationTests {
    @Autowired
    AccommodationService accommodationService;

    @Test
    public void testFetchingAllAccommodations() {
        AccommodationController homeController = new AccommodationController(accommodationService);
        List<Accommodation> result = homeController.fetchAccommodations(new AccommodationDTO());
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
