package sk.zvjs.holup.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserResponseDTO {
    private Long id;
    private String email;

    public UserResponseDTO(User user) {
        this(user.getId(), user.getEmail());
    }
}
