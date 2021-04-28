package sk.zvjs.holup.accommodation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends CrudRepository<Accommodation, Integer> {
    @Query(
            value = "SELECT * FROM Accommodation " +
            " WHERE (:type IS NULL OR type IN (:type))" +
            " AND " +
            " (:age IS NULL OR age = cast(:age AS text))" +
            " AND " +
            " (:gender IS NULL OR gender = cast(:gender AS text)) " +
            " AND " +
            " (:region IS NULL OR region IN (:region))", nativeQuery = true
    )
    List<Accommodation> findAllAccommodationsByDTO(
            @Param("type") List<String> type,
            @Param("age") String age,
            @Param("gender") String gender,
            @Param("region") List<String> region
    );
}
