package app.web.dto;

import app.user.model.Country;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterRequest {

    @NotNull
    @Length(min = 6)
    private String username;
    @NotNull
    @Length(min = 6)
    private String password;
    @NotNull
    private Country country;

}
