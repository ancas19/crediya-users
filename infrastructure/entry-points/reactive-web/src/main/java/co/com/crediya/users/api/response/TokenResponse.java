package co.com.crediya.users.api.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TokenResponse {
    private String names;
    private String lastName;
    private String email;
    private String identifcation;
    private String token;
}
