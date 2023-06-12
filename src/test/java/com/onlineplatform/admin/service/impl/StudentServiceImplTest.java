package com.onlineplatform.admin.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onlineplatform.admin.db.entities.Course;
import com.onlineplatform.admin.db.entities.Student;
import com.onlineplatform.admin.db.entities.User;
import com.onlineplatform.admin.db.repo.StudentRepo;
import com.onlineplatform.admin.service.UserService;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepo studentDao;

    @Mock
    private UserService userService;

    @Test
    void testLoadStudentById() {
        Student student = new Student();
        student.setStudentId(1L);

        when(studentDao.findById(any())).thenReturn(Optional.of(student));

        Student actualStudent = studentService.loadStudentById(1L);

        assertEquals(student, actualStudent);
    }

    @Test
    void testExceptionForNotFoundUserById() {
        assertThrows(EntityNotFoundException.class, ()-> studentService.loadStudentById(any()));
    }

    @Test
    void testFindStudentsByName() {
        String name = "name";
        studentService.findStudentsByName(name);
        verify(studentDao).findStudentsByName(name);
    }

    @Test
    void testLoadStudentByEmail() {
        String email = "test@gmail.com";
        studentService.loadStudentByEmail(email);
        verify(studentDao).findStrudentByEmail(email);
    }

    @Test
    void testCreateStudent() {
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword("adpass");

        when(userService.createUser(any(),any())).thenReturn(user);

        studentService.createStudent("FN","LN","master","user@gmail.com","adpass");

        verify(studentDao).save(any());
    }

    @Test
    void testUpdateStudent() {
        Student student = new Student();
        student.setStudentId(1L);

        studentService.updateStudent(student);

        verify(studentDao).save(student);
    }

    @Test
    void testFetchStudents() {
        studentService.fetchStudents();
        verify(studentDao, times(1)).findAll();
    }

    @Test
    void testRemoveStudent() {
        Student student = new Student();
        student.setStudentId(1L);

        Course course = new Course();
        course.setCourseId(1L);
        student.getCourses().add(course);

        when(studentDao.findById(any())).thenReturn(Optional.of(student));

        studentService.removeStudent(1L);

        verify(studentDao).deleteById(any());
    }
}