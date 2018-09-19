package com.betasquirrell.unit.tests.UsersController;

import com.betasquirrel.controller.UsersController;
import com.betasquirrel.model.ListResponse;
import com.betasquirrel.model.User;
import com.betasquirrel.repository.UserRepository;
import com.flextrade.jfixture.JFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class GivenAUsersController_WhenTheGetAllUsersMethodIsInvoked {
    private List<User> users;
    private ListResponse expectedResponse;
    private ListResponse actualResponse;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    public void Setup(){
        MockitoAnnotations.initMocks(this);

        JFixture fixture = new JFixture();
        users = (ArrayList)fixture.collections().createCollection(User.class);
        expectedResponse = TestHelper.CreateResponseFromUsers(users);

        when(userRepository.findAll()).thenReturn(users);

        actualResponse = usersController.getAllUsers(TestHelper.response);
    }

    @Test
    void ThenTheFindAllMethodIsInvokedOnTheUserRepository() {
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void ThenTheCorrectListOfUsersIsReturned() {
        assertThat(expectedResponse, sameBeanAs(actualResponse));
    }
}
