package co.com.crediya.users.model.commos.utils;


import co.com.crediya.users.model.roles.models.Roles;
import co.com.crediya.users.model.users.models.Users;

import java.time.LocalDate;
import java.util.UUID;

public class MocksTest {
    private MocksTest() {}

    public static Roles createRole(){
        return Roles.builder()
                .id(UUID.randomUUID())
                .name("Admin")
                .title("Administrator")
                .build();
    }

    public static Users createUser(){
        return Users.builder()
                .id(UUID.randomUUID())
                .identification("123456789")
                .birthDate(LocalDate.now().minusYears(19))
                .names("John")
                .lastName("Doe")
                .address("123 Main St")
                .phone("555-1234")
                .email("correo@email.com")
                .password("password")
                .roleId(createRole().getId())
                .status("Active")
                .roleName("roleName")
                .build();
    }
}
