package co.com.crediya.users.model.commos.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Token {
    private String names;
    private String lastName;
    private String email;
    private String identifcation;
    private String token;
}
