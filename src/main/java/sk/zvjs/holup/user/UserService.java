package sk.zvjs.holup.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> fetchUserById(UUID id) {
        return repository.findById(id);
    }

    public Optional<User> findByConvictedNumber(Long convictedNumber) {
        return repository.findByConvictedNumber(convictedNumber);
    }

    public String checkApiKey(String apiKey) {
        Optional<User> user = repository.findByApiKey(apiKey);
        if (user.isPresent()) {
            return user.get().getApiKey();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nesprávny api kľúč");
    }

    public UserResponseDTO authenticate(Long convictedNumber, String password) {
        Optional<User> user = repository.findByConvictedNumber(convictedNumber);
        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                return new UserResponseDTO(user.get().getId(), user.get().getConvictedNumber(), user.get().getApiKey());
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nesprávne číslo odsúdeného alebo heslo");
    }

    public User createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
}