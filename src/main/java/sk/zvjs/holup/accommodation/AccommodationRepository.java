package sk.zvjs.holup.accommodation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends CrudRepository<Accommodation, Integer>, AccommodationRepositoryCustom {
}
