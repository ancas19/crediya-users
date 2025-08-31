package co.com.crediya.users.model.users.models;
import co.com.crediya.users.model.commos.models.AbstractAuditoria;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@SuperBuilder()
@NoArgsConstructor
@AllArgsConstructor
public class Users  extends AbstractAuditoria {
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
    private String status;
}
