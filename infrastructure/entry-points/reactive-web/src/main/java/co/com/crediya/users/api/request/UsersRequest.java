package co.com.crediya.users.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UsersRequest {
    private UUID id;
    @NotNull(message="the field is required")
    @NotEmpty(message="the field cannot be empty")
    @Pattern(regexp = "^\\d{10,15}$", message = "the field must be between 10 and 15 digits")
    private String identification;
    @NotNull(message="the field is required")
    @NotEmpty(message="the field cannot be empty")
    private String names;
    @NotNull(message="the field is required")
    @NotEmpty(message="the field cannot be empty")
    private String lastName;
    @NotNull(message="the field is required")
    @Past(message="the date must be in the past")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    @NotNull(message="the field is required")
    @NotEmpty(message="the field cannot be empty")
    private String address;
    @NotNull(message="the field is required")
    @NotEmpty(message="the field cannot be empty")
    @Pattern(regexp = "^\\+?\\d{10,15}$", message="the field must be between 10 and 15 digits and can start with +")
    private String phone;
    @NotNull(message="the field is required")
    @DecimalMin(value = "0.0",  message = "the field must be greater than 0")
    @DecimalMax(value="15000000", message = "the field must be less than 15000000")
    private BigDecimal baseSalary;
    @NotNull(message="the field is required")
    @NotEmpty(message="the field cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "The email format is invalid. It should be like juam@email.com")
    private String email;
    @NotNull(message="the field is required")
    @NotEmpty(message="the field cannot be empty")
    @Size(min=8, message="the field must be at least 8 characters long")
    private String password;
    @NotNull(message="the field is required")
    @Pattern(regexp = "[A-Z]+$", message = "the field must be uppercase letters only")
    private String roleName;
}
