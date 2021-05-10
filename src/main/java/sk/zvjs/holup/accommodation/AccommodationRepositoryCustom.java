package sk.zvjs.holup.accommodation;

import java.util.List;
import java.util.Set;

public interface AccommodationRepositoryCustom {
    List<Accommodation> findAccommodationsByAllParams(
            Set<String> types,
            String gender,
            Set<String> regions,
            Set<String> districts,
            Set<String> ages
    );
}
