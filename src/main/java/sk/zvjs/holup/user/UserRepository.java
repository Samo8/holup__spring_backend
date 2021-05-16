package sk.zvjs.holup.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByConvictedNumber(Long convictedNumber);
    Optional<User> findByApiKey(String apiKey);
}
