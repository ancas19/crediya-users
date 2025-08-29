package co.com.crediya.users.usecase.users;

import co.com.crediya.users.model.commos.enums.ErrorMessages;
import co.com.crediya.users.model.commos.exception.BadRequestException;
import co.com.crediya.users.model.users.gateways.UsersRepositoryPort;
import co.com.crediya.users.model.users.models.Users;
import co.com.crediya.users.usecase.roles.RolesUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class UsersUseCase {
    private final UsersRepositoryPort usersRepositoryPort;
    private final RolesUseCase rolesUseCase;

    public Mono<Users> createUser(Users userInformation) {
        return usersRepositoryPort.existsByEmail(userInformation.getEmail())
                .flatMap(existsByEmail -> {
                            if (existsByEmail) {
                                throw new BadRequestException(ErrorMessages.ERROR_MESSAGE_EMAIL_ALREADY_EXISTS.getMessage());
                            }
                            return usersRepositoryPort.existsByDocumentNumber(userInformation.getIdentification());
                        }
                ).flatMap(existsByDocumentNumber -> {
                            if (existsByDocumentNumber) {
                                throw new BadRequestException(ErrorMessages.ERROR_MESSAGE_DOCUMENT_ALREADY_EXISTS.getMessage());
                            }
                            return rolesUseCase.findByName(userInformation.getRoleName());
                        }
                ).flatMap(roleFound -> {
                            userInformation.setRoleId(roleFound.getId());
                             return this.usersRepositoryPort.createUser(userInformation);
                        }
                );
    }
}
