package co.com.crediya.users.r2dbc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@Builder
@Table(name="roles")
@NoArgsConstructor
@AllArgsConstructor
public class RolesEntity {
    @Id
    @Column("id")
    private UUID id;

    @Column("titulo")
    private String title;

    @Column("nombre")
    private String name;
}
