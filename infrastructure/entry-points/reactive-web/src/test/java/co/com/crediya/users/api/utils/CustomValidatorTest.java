package co.com.crediya.users.api.utils;

import co.com.crediya.users.api.request.UsersRequest;
import co.com.crediya.users.model.commos.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

@ExtendWith(MockitoExtension.class)
class CustomValidatorTest {
    @Mock
    private Validator validator;
    @InjectMocks
    private CustomValidator customValidator;
    private UsersRequest usersRequest;

    @BeforeEach
    void setUp() {
        usersRequest = new UsersRequest();
    }

    @Test
    void validateReturnsMonoErrorWhenValidationErrorsExist() {
        usersRequest=new UsersRequest();
        Errors errors = new BeanPropertyBindingResult(usersRequest, usersRequest.getClass().getName());
        errors.rejectValue("names", "error.code", "Field error message");

        doAnswer(invocation -> {
            ((Errors) invocation.getArgument(1)).addAllErrors(errors);
            return null;
        }).when(validator).validate(any(), any());

        StepVerifier.create(customValidator.validate(usersRequest))
                .expectErrorMatches(throwable -> throwable instanceof BadRequestException &&
                        throwable.getMessage().contains("names: Field error message"))
                .verify();
    }

    @Test
    void validateReturnsMonoJustWhenNoValidationErrorsExist() {
        StepVerifier.create(customValidator.validate(usersRequest))
                .expectNext(usersRequest)
                .verifyComplete();
    }
}