package co.com.crediya.users.model.commos.enums;

public enum Constants {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String value;

    Constants(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

}
