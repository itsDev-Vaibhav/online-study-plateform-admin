package com.onlineplatform.admin.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.onlineplatform.admin.AbstractTest;
import com.onlineplatform.admin.db.entities.Student;
import com.onlineplatform.admin.db.repo.StudentRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/resources/db/clearAll.sql","file:src/test/resources/db/javacorner-admin-db.sql"})
class StudentDaoTest extends AbstractTest {

    @Autowired
    private StudentRepo studentDao;

    @Test
    void testFindStudentsByName() {
        List<Student> students = studentDao.findStudentsByName("std2LN");
        assertEquals(1, students.size());
    }

    @Test
    void testFindStudentByEmail() {
        Student expectedStudent = new Student();
        expectedStudent.setStudentId(1L);
        expectedStudent.setFirstName("std1FN");
        expectedStudent.setLastName("std1LN");
        expectedStudent.setLevel("beginner");
        Student student = studentDao.findStrudentByEmail("stdUser1@gmail.com");
        assertEquals(expectedStudent, student);
    }
}