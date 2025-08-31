package co.com.crediya.users.model.commos.utils;


import co.com.crediya.users.model.roles.models.Roles;

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
}
