package com.onlineplatform.admin.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.onlineplatform.admin.AbstractTest;
import com.onlineplatform.admin.db.entities.Course;
import com.onlineplatform.admin.db.repo.CourseRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/resources/db/clearAll.sql","file:src/test/resources/db/javacorner-admin-db.sql"})
class CourseDaoTest extends AbstractTest {

    @Autowired
    private CourseRepo courseDao;

    @Test
    void testFindCoursesByCourseNameContains() {
        List<Course> courses = courseDao.findCoursesByCourseNameContains("Spring");
        int expectResult = 2;
        assertEquals(expectResult, courses.size());
    }

    @Test
    void testGetCoursesByStudentId() {
        List<Course> courses = courseDao.getCoursesByStudentId(1L);
        int expectedResult = 1;
        assertEquals(expectedResult, courses.size());
    }
}