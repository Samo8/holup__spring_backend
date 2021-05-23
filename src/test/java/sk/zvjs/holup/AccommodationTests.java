package sk.zvjs.holup;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sk.zvjs.holup.accommodation.AccommodationController;
import sk.zvjs.holup.accommodation.AccommodationDTO;
import sk.zvjs.holup.accommodation.AccommodationService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccommodationTests {
    @Autowired
    AccommodationService accommodationService;

    @Test
    public void testFetchingAllAccommodations() {
        var homeController = new AccommodationController(accommodationService);
        var accommodations = homeController.fetchAccommodations(new AccommodationDTO());
        assertNotNull(accommodations);
        assertFalse(accommodations.isEmpty());
    }

    @Test
    public void testFetchingAccommodationsByOneType() {
        var homeController = new AccommodationController(accommodationService);
        Set<String> types = new HashSet<>();
        types.add("útulok");
        var accommodations = homeController.fetchAccommodations(
                AccommodationDTO.builder()
                        .types(types)
                        .build());
        assertNotNull(accommodations);
        assertFalse(accommodations.isEmpty());
    }

    @Test
    public void testFetchingAccommodationsByNotExistingType() {
        var homeController = new AccommodationController(accommodationService);
        Set<String> types = new HashSet<>();
        types.add("neexistujuci typ");
        var accommodations = homeController.fetchAccommodations(
                AccommodationDTO.builder()
                        .types(types)
                        .build());
        assertNotNull(accommodations);
        assertTrue(accommodations.isEmpty());
    }

    @Test
    public void testFetchingAccommodationsByMoreTypes() {
        var homeController = new AccommodationController(accommodationService);
        Set<String> types = new HashSet<>();
        types.add("útulok");
        types.add("nocľaháreň");
        var accommodations = homeController.fetchAccommodations(
                AccommodationDTO.builder()
                        .types(types)
                        .build());
        assertNotNull(accommodations);
        assertFalse(accommodations.isEmpty());
    }

    @Test
    public void testFetchingAccommodationsByOneRegion() {
        var homeController = new AccommodationController(accommodationService);
        Set<String> regions = new HashSet<>();
        regions.add("Bratislavský kraj");
        var accommodations = homeController.fetchAccommodations(
                AccommodationDTO.builder()
                        .regions(regions)
                        .build());
        assertNotNull(accommodations);
        assertFalse(accommodations.isEmpty());
        for (var accommodation : accommodations) {
            assertEquals(accommodation.getAddress().getRegion(), "Bratislavský kraj");
        }
    }

    @Test
    public void testFetchingAccommodationsByNotExistingRegion() {
        var homeController = new AccommodationController(accommodationService);
        Set<String> regions = new HashSet<>();
        regions.add("Vymyslený kraj");
        var result = homeController.fetchAccommodations(
                AccommodationDTO.builder()
                        .regions(regions)
                        .build());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFetchingAccommodationsByGender() {
        var homeController = new AccommodationController(accommodationService);
        var gender = "Muži";
        var accommodations = homeController.fetchAccommodations(
                AccommodationDTO.builder()
                        .gender(gender)
                        .build());
        assertNotNull(accommodations);
        assertFalse(accommodations.isEmpty());

        for (var accommodation : accommodations) {
            assertEquals(accommodation.getGender(), gender);
        }
    }
}
