package co.com.crediya.users.r2dbc.entity;



import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@Table(name="clients")
@NoArgsConstructor
@AllArgsConstructor
public class UsersEntity extends AbstractAuditoriaEntity {
    @Id
    @Column("id")
    private UUID id;

    @Column("identificacion")
    private String identification;

    @Column("nombres")
    private String names;

    @Column("apellidos")
    private String lastName;

    @Column("fecha_nacimiento")
    private LocalDate birthDate;

    @Column("direccion")
    private String address;

    @Column("telefono")
    private String phone;

    @Column("salario_base")
    private BigDecimal baseSalary;

    @Column("correo_electronico")
    private String email;

    @Column("estado")
    private String status;

    @Column("password")
    private String password;

    @Column("role_id")
    private UUID roleId;
}
