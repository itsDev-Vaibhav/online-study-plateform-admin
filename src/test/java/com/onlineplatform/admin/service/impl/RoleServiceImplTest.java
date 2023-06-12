package com.onlineplatform.admin.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onlineplatform.admin.db.repo.RoleRepo;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleRepo roleDao;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    void testLoadRoleByName() {
        roleService.loadRoleByName("Admin");
        verify(roleDao).findByRoleName(any());
    }

    @Test
    void testCreateRole() {
        roleService.createRole("Admin");
        verify(roleDao, times(1)).save(any());
    }
}