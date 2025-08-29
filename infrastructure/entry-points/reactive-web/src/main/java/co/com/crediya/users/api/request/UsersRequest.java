package co.com.crediya.users.api.request;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UsersRequest {
    private String identification;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private BigDecimal baseSalary;
    private String email;
    private String password;
    private String roleName;
}
