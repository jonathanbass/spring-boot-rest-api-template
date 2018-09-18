package com.betasquirrell.unit.tests;

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

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GivenAUsersController {
    private List<User> usersList;
    private List<User> actualUsers;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    public void WhenTheGetAllUsersMethodIsInvoked(){
        MockitoAnnotations.initMocks(this);
        JFixture fixture = new JFixture();
        User user1 = fixture.create(User.class);
        User user2 = fixture.create(User.class);

        usersList = new ArrayList<User>();
        usersList.add(user1);
        usersList.add(user2);

        when(userRepository.findAll()).thenReturn(usersList);

        actualUsers = usersController.getAllUserNew(TestHelper.response);
    }

    @Test
    void ThenTheFindAllMethodIsInvokedOnTheUserRepository() {
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void ThenTheCorrectListOfUsersIsReturned() {
        assertSame(usersList, actualUsers);
    }
}