package co.com.crediya.users.api.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class IdentificationRequest {
    @NotNull(message="the field is required")
    @NotEmpty(message="the field cannot be empty")
    @Pattern(regexp = "^\\d{10,15}$", message = "the field must be between 10 and 15 digits")
    private String identification;
}
