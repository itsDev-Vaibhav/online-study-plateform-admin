package com.onlineplatform.admin.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.onlineplatform.admin.AbstractTest;
import com.onlineplatform.admin.db.entities.Instructor;
import com.onlineplatform.admin.db.repo.InstructorRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/resources/db/clearAll.sql","file:src/test/resources/db/javacorner-admin-db.sql"})
class InstructorDaoTest extends AbstractTest {

    @Autowired
    private InstructorRepo instructorDao;

    @Test
    void testFindInstructorsByName() {
        List<Instructor> instructors = instructorDao.findInstructorsByName("instructor1FN");
        int expectedValue = 1;
        assertEquals(expectedValue, instructors.size());
    }

    @Test
    void testFindInstructorByEmail() {
        Instructor expectedInstructor = new Instructor();
        expectedInstructor.setInstructorId(1L);
        expectedInstructor.setFirstName("instructor1FN");
        expectedInstructor.setLastName("instructor1LN");
        expectedInstructor.setSummery("Experienced Instructor");

        Instructor instructor = instructorDao.findInstructorByEmail("instructorUser1@gmail.com");

        assertEquals(expectedInstructor, instructor);
    }

    @Test
    void testFindInstructorByNotExistingEmail(){
        Instructor instructor = instructorDao.findInstructorByEmail("javacorner@gmail.com");
        assertNull(instructor);
    }
}