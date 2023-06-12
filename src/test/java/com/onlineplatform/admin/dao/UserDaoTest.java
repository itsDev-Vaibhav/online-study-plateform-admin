package com.onlineplatform.admin.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.onlineplatform.admin.AbstractTest;
import com.onlineplatform.admin.db.entities.User;
import com.onlineplatform.admin.db.repo.UserRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/resources/db/clearAll.sql","file:src/test/resources/db/javacorner-admin-db.sql"})
class UserDaoTest extends AbstractTest {

    @Autowired
    private UserRepo userDao;

    @Test
    void testFindByEmail() {
        User user = userDao.findByEmail("instructorUser2@gmail.com");
        Long expectedId = 4L;
        assertEquals(expectedId, user.getUserId());
    }

    @Test
    void testFindingNonExistingUserWithEmail() {
        User user = userDao.findByEmail("nonExistingEMail@gmail.com");
        assertNull(user);
    }
}