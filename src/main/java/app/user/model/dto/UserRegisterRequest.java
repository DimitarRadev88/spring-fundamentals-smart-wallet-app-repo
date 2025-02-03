package app.user.model.dto;

import app.user.model.enumeration.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterRequest {

    @NotBlank(message = "Username must not be blank")
    @Length(min = 6, message = "Username must be at least 6 characters")
    private String username;
    @NotBlank(message = "Password must be at least 6 characters")
    @Length(min = 6, message = "Password must be at least 6 characters")
    private String password;
    @NotNull
    private Country country;

}
