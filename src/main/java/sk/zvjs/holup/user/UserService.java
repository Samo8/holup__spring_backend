package sk.zvjs.holup.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserService {
    private final UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nepsrávne číslo odsúdeného alebo heslo");
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
