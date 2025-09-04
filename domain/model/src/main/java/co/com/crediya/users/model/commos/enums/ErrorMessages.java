package co.com.crediya.users.model.commos.enums;

public enum ErrorMessages {
    ERROR_MESSAGE_USER_NOT_FOUND("User not found"),
    ERROR_MESSAGE_GLOBAL_EXCEPTION("Internal server error, contact with support for help"),
    ERROR_MESSAGE_ROLE_NOT_FOUND("Not role found with the given name (%s)"),
    ERROR_MESSAGE_EMAIL_ALREADY_EXISTS("The email already exists"),
    ERROR_MESSAGE_DOCUMENT_ALREADY_EXISTS("The document number already exists"),
    ERROR_MESSAGE_UNDERAGE("The user must be at least 18 years old to register"),
    ERROR_MESSAGE_USER_NOT_FOUND_BY_ID("User not found with the given id (%s)"),
    ERROR_MESSAGE_BAD_CREDENTIALS("Bad credentials. Email or password incorrect");
    private final String message;

    ErrorMessages(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
