package sk.zvjs.holup.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthDTO {
    @NotNull
    private Long convictedNumber;
    @NotNull
    @Size(min = 6, message = "Heslo musí mať aspoň 6 znakov")
    private String password;

    User toUser() {
        return new User(this.convictedNumber, this.password);
    }
}
