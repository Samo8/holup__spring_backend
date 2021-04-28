package sk.zvjs.holup.release;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReleaseRepository extends CrudRepository<Release, Integer> {
    Optional<Release> findReleaseByUserId(UUID uuid);
}
