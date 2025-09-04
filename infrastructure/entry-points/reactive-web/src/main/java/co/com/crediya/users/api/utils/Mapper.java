package co.com.crediya.users.api.utils;

import co.com.crediya.users.api.request.AuthenticationRequest;
import co.com.crediya.users.api.request.UsersRequest;
import co.com.crediya.users.api.response.TokenResponse;
import co.com.crediya.users.api.response.UsersResponse;
import co.com.crediya.users.model.commos.models.Authentication;
import co.com.crediya.users.model.commos.models.Token;
import co.com.crediya.users.model.users.models.Users;

public class Mapper {
    public static Users toUsersModel(UsersRequest usersRequest) {
        return Users.builder()
                .identification(usersRequest.getIdentification())
                .names(usersRequest.getNames())
                .lastName(usersRequest.getLastName())
                .birthDate(usersRequest.getBirthDate())
                .address(usersRequest.getAddress())
                .phone(usersRequest.getPhone())
                .baseSalary(usersRequest.getBaseSalary())
                .email(usersRequest.getEmail())
                .password(usersRequest.getPassword())
                .roleName(usersRequest.getRoleName())
                .build();
    }

    public static UsersResponse toUsersResponse(Users users) {
        return UsersResponse.builder()
                .id(users.getId())
                .identification(users.getIdentification())
                .names(users.getNames())
                .lastName(users.getLastName())
                .birthDate(users.getBirthDate())
                .address(users.getAddress())
                .phone(users.getPhone())
                .baseSalary(users.getBaseSalary())
                .email(users.getEmail())
                .build();
    }

    public static Authentication toAuthenticationModel(AuthenticationRequest authenticationRequest) {
        return Authentication.builder()
                .email(authenticationRequest.getEmail())
                .password(authenticationRequest.getPassword())
                .build();
    }

    public static TokenResponse toTokenResponse(Token token){
        return TokenResponse.builder()
                .names(token.getNames())
                .lastName(token.getLastName())
                .email(token.getEmail())
                .identifcation(token.getIdentifcation())
                .token(token.getToken())
                .build();
    }

}
