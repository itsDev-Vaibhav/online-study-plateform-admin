package com.onlineplatform.admin.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onlineplatform.admin.db.entities.Role;
import com.onlineplatform.admin.db.entities.User;
import com.onlineplatform.admin.db.repo.RoleRepo;
import com.onlineplatform.admin.db.repo.UserRepo;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepo userDao;

    @Mock
    private User mockedUser;

    @Mock
    private RoleRepo roleDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testLoadUserByEmail() {
        userService.loadUserByEmail("email@gmail.com");
        verify(userDao, times(1)).findByEmail(any());
    }

    @Test
    void testCreateUser() {
        String email = "user@gmail.com";
        String password = "pass";
        User user = new User(email, password);


        userService.createUser(email, password);

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(userDao).save(argumentCaptor.capture());

        User capturedUser = argumentCaptor.getValue();

        assertEquals(user, capturedUser);

    }

    @Test
    void testAssignRoleToUser() {
        Role role = new Role();
        role.setRoleId(1L);

        when(userDao.findByEmail(any())).thenReturn(mockedUser);
        when(roleDao.findByRoleName(any())).thenReturn(role);

        userService.assignRoleToUser("email@gmail.com","pass");

        verify(mockedUser, times(1)).assignRoleToUser(role);

    }
}