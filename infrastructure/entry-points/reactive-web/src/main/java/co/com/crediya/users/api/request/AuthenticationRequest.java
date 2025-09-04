package co.com.crediya.users.api.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthenticationRequest {
    @NotNull(message="the field is required")
    @NotEmpty(message="the field cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "The email format is invalid. It should be like juam@email.com")
    private String email;
    @NotNull(message="the field is required")
    @NotEmpty(message="the field cannot be empty")
    @Size(min=8, message="the field must be at least 8 characters long")
    private String password;
}
