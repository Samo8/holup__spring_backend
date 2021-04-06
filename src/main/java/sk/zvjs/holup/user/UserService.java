package sk.zvjs.holup.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;


@Service
public class UserService {
    private final UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;

    }

    public List<User> fetchAllUsers() {
        List<User> users = new ArrayList<>();
        repository.findAll().forEach(users::add);
        return users;
    }

    public Optional<User> fetchUserById(UUID id) {
        return repository.findById(id);
    }
    public Optional<User> findByEmail(String email) { return repository.findByEmail(email); }

    public String checkApiKey(String apiKey) {
        Optional<User> user = repository.findByApiKey(apiKey);
        if (user.isPresent()) {
            return user.get().getApiKey();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid api key");
    }

    public UserResponseDTO authenticate(String email, String password) {
        Optional<User> user = repository.findByEmail(email);
        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                return new UserResponseDTO(user.get().getId(), user.get().getEmail(), user.get().getApiKey());
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid password");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with " + email + " not found");
    }

    public User createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
