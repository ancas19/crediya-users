package co.com.crediya.users.model.commos.enums;

public enum Roles {
    ADMIN("ADMIN"),
    ASESOR("ASESOR");


    private final String value;

    Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
