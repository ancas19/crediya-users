package co.com.crediya.users.model.roles.models;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Roles {
    private UUID id;
    private String title;
    private String name;
}
