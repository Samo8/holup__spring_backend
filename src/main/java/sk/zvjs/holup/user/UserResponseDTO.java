package sk.zvjs.holup.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserResponseDTO {
    private UUID id;
    private Long convictedNumber;
    private String apiKey;

    public UserResponseDTO(User user) {
        this(user.getId(), user.getConvictedNumber(), user.getApiKey());
    }
}
