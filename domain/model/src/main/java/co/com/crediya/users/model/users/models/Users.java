package co.com.crediya.users.model.users.models;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Users {
    private UUID id;
    private String identification;
    private String names;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private BigDecimal baseSalary;
    private String email;
    private String password;
    private UUID roleId;
    private String roleName;
}
