package sk.zvjs.holup.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> fetchAllUsers() {
        return repository.findAll();
    }

    public Optional<User> fetchUserByUUID(UUID uuid) {
        return repository.findByUuid(uuid);
    }

    public User createNewUser(User user) {
        return repository.save(user);
    }
}
