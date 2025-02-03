package app.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginRequest {

    @NotBlank(message = "Username must be at least 6 characters")
    @Length(min = 6, message = "Username must be at least 6 characters")
    private String username;
    @NotBlank(message = "Username must be at least 6 characters")
    @Length(min = 6, message = "Username must be at least 6 characters")
    private String password;

}
