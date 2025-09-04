package co.com.crediya.users.model.commos.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Authentication {
    private String email;
    private String password;
}
