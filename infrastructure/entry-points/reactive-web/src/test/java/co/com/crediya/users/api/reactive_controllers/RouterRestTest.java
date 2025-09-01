package co.com.crediya.users.api.reactive_controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RouterRestTest {
    @Mock
    private UsersRequestHandler usersRequestHandler;
    @InjectMocks
    private RouterRest routerRest;

    @Test
    void routerFunctionTest(){
        assertNotNull(routerRest.routerFunction());
    }

}