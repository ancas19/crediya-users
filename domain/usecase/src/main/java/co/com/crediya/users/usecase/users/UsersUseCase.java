package co.com.crediya.users.usecase.users;

import co.com.crediya.users.model.commos.enums.ErrorMessages;
import co.com.crediya.users.model.commos.exception.BadRequestException;
import co.com.crediya.users.model.users.gateways.UsersRepositoryPort;
import co.com.crediya.users.model.users.models.Users;
import co.com.crediya.users.usecase.roles.RolesUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RequiredArgsConstructor
public class UsersUseCase {
    private final UsersRepositoryPort usersRepositoryPort;
    private final RolesUseCase rolesUseCase;

    public Mono<Users> createUser(Users userInformation) {
        return validateUserData(userInformation)
                .then(checkEmailNotExists(userInformation.getEmail()))
                .then(checkDocumentNotExists(userInformation.getIdentification()))
                .then(rolesUseCase.findByName(userInformation.getRoleName()))
                .flatMap(role -> {
                    userInformation.setRoleId(role.getId());
                    return usersRepositoryPort.createUser(userInformation);
                });
    }

    private Mono<Void> validateUserData(Users user) {
        LocalDate today = LocalDate.now();
        int age = today.getYear() - user.getBirthDate().getYear();
        if (age < 18) {
            return Mono.error(new BadRequestException(ErrorMessages.ERROR_MESSAGE_UNDERAGE.getMessage()));
        }
        return Mono.empty();
    }

    private Mono<Void> checkEmailNotExists(String email) {
        return usersRepositoryPort.existsByEmail(email)
                .flatMap(exists -> exists
                        ? Mono.error(new BadRequestException(ErrorMessages.ERROR_MESSAGE_EMAIL_ALREADY_EXISTS.getMessage()))
                        : Mono.empty());
    }

    private Mono<Void> checkDocumentNotExists(String documentNumber) {
        return usersRepositoryPort.existsByDocumentNumber(documentNumber)
                .flatMap(exists -> exists
                        ? Mono.error(new BadRequestException(ErrorMessages.ERROR_MESSAGE_DOCUMENT_ALREADY_EXISTS.getMessage()))
                        : Mono.empty());
    }
}

