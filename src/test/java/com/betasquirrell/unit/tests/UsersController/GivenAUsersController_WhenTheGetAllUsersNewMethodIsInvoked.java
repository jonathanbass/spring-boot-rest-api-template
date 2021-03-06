package com.betasquirrell.unit.tests.UsersController;

import com.betasquirrel.controller.UsersController;
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

class GivenAUsersController_WhenTheGetAllUsersNewMethodIsInvoked {
    private List<User> usersList;
    private List<User> actualUsers;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    public void Setup(){
        MockitoAnnotations.initMocks(this);

        JFixture fixture = new JFixture();
        usersList = (ArrayList)fixture.collections().createCollection(User.class);

        when(userRepository.findAll()).thenReturn(usersList);

        actualUsers = usersController.getAllUsersNew(TestHelper.response);
    }

    @Test
    void ThenTheFindAllMethodIsInvokedOnTheUserRepository() {
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void ThenTheCorrectListOfUsersIsReturned() {
        assertThat(usersList, sameBeanAs(actualUsers));
    }
}