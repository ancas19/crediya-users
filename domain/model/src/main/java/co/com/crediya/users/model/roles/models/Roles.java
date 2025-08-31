package co.com.crediya.users.model.roles.models;
import co.com.crediya.users.model.commos.models.AbstractAuditoria;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder()
@NoArgsConstructor
@AllArgsConstructor
public class Roles extends AbstractAuditoria {
    private UUID id;
    private String title;
    private String name;
}
