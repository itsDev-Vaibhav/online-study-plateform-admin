package com.onlineplatform.admin.service.impl;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onlineplatform.admin.db.entities.Course;
import com.onlineplatform.admin.db.entities.Instructor;
import com.onlineplatform.admin.db.entities.Student;
import com.onlineplatform.admin.db.repo.CourseRepo;
import com.onlineplatform.admin.db.repo.InstructorRepo;
import com.onlineplatform.admin.db.repo.StudentRepo;
import com.onlineplatform.admin.service.CourseService;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepo courseDao;

    @Mock
    private InstructorRepo instructorDao;

    @Mock
    private StudentRepo studentDao;

    @InjectMocks
    private CourseService courseService;

    @Test
    void testLoadCourseById() {
        Course course = new Course();
        course.setCourseId(1L);

        when(courseDao.findById(any())).thenReturn(Optional.of(course));

        Course actualCourse = courseService.LoadCourseById(1L);

        assertEquals(course, actualCourse);

    }

    @Test
    void testExceptionForNotFoundCourseById() {
        assertThrows(EntityNotFoundException.class,()->courseService.LoadCourseById(2L));
    }

    @Test
    void testCreateCourse() {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(1L);

        when(instructorDao.findById(any())).thenReturn(Optional.of(instructor));

        courseService.createCourse("JPA","1h 30min","Hello JPA", 1L);

        verify(courseDao).save(any());
    }

    @Test
    void testCreateOrUpdateCourse() {
        Course course = new Course();
        course.setCourseId(1L);

        courseService.createOrUpdateCourse(course);

        ArgumentCaptor<Course> argumentCaptor = ArgumentCaptor.forClass(Course.class);

        verify(courseDao).save(argumentCaptor.capture());

        Course capturedValue = argumentCaptor.getValue();

        assertEquals(course, capturedValue);
    }

    @Test
    void testFindCoursesByCourseName() {
        String courseName ="JPA";
        courseService.findCourseByCouseName(courseName);
        verify(courseDao).findCoursesByCourseNameContains(courseName);
    }

    @Test
    void testAssignStudentToCourse() {
        Student student = new Student();
        student.setStudentId(2L);
        Course course = new Course();
        course.setCourseId(1L);

        when(studentDao.findById(any())).thenReturn(Optional.of(student));
        when(courseDao.findById(any())).thenReturn(Optional.of(course));

        courseService.assignStudenttoCourse(1L, 1L);
    }

    @Test
    void testFetchAll() {
        courseService.fetchAll();
        verify(courseDao).findAll();
    }

    @Test
    void testFetchCoursesForStudent() {
        courseService.fetchCousesForStudent(1L);
        verify(courseDao).getCoursesByStudentId(1L);
    }

    @Test
    void testRemoveCourse() {
        courseService.removeCourse(1L);
        verify(courseDao).deleteById(1L);
    }
}