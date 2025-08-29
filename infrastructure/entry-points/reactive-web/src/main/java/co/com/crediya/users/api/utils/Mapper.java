package co.com.crediya.users.api.utils;

import co.com.crediya.users.api.request.UsersRequest;
import co.com.crediya.users.api.response.UsersResponse;
import co.com.crediya.users.model.users.models.Users;

public class Mapper {
    public static Users toModel(UsersRequest usersRequest) {
        return Users.builder()
                .identification(usersRequest.getIdentification())
                .firstName(usersRequest.getFirstName())
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

    public static UsersResponse toResponse(Users users) {
        return UsersResponse.builder()
                .identification(users.getIdentification())
                .firstName(users.getFirstName())
                .lastName(users.getLastName())
                .birthDate(users.getBirthDate())
                .address(users.getAddress())
                .phone(users.getPhone())
                .baseSalary(users.getBaseSalary())
                .email(users.getEmail())
                .build();
    }

}
