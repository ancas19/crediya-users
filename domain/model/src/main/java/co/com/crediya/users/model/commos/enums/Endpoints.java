package co.com.crediya.users.model.commos.enums;

public enum Endpoints {
    USERS_ENDPOINT("/users"),
    AUTH_LOGIN("/auth/login");

    private final String value;

    Endpoints(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
