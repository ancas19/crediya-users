package co.com.crediya.users.model.users.models;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UsersAuthentication {
    private UUID id;
    private String identification;
    private String names;
    private String lastName;
    private String email;
    private String password;
    private String roleName;
    private String roleTitle;
}
