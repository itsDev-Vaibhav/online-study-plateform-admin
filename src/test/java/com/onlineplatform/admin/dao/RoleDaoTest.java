package com.onlineplatform.admin.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.onlineplatform.admin.AbstractTest;
import com.onlineplatform.admin.db.entities.Role;
import com.onlineplatform.admin.db.repo.RoleRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/resources/db/clearAll.sql","file:src/test/resources/db/javacorner-admin-db.sql"})
class RoleDaoTest extends AbstractTest {

    @Autowired
    private RoleRepo roleDao;

    @Test
    void testFindByName() {
        String roleName = "Admin";
        Role role = roleDao.findByRoleName(roleName);
        assertEquals(roleName, role.getRoleName());
    }

    @Test
    void testFindNonExistingRole() {
        String roleName = "NewRole";
        Role role = roleDao.findByRoleName(roleName);
        assertNull(role);
    }
}