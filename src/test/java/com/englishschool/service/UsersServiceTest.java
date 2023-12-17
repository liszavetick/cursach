package com.englishschool.service;

import com.englishschool.model.Users;
import com.englishschool.repo.UsersRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UsersServiceTest {

    @Mock
    private UsersRepo usersRepo;

    @InjectMocks
    private UsersService usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsername_Success() {

        String username = "test";
        String password = "testPassword";  // Указываете пароль здесь
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);  // Устанавливаете пароль

        when(usersRepo.findByUsername(username)).thenReturn(user);

        UserDetails userDetails = usersService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());  // Проверяете пароль
        verify(usersRepo, times(1)).findByUsername(username);
    }

}
