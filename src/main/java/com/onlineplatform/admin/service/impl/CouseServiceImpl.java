package com.onlineplatform.admin.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.onlineplatform.admin.db.entities.Course;
import com.onlineplatform.admin.db.entities.Instructor;
import com.onlineplatform.admin.db.entities.Student;
import com.onlineplatform.admin.db.repo.CourseRepo;
import com.onlineplatform.admin.db.repo.InstructorRepo;
import com.onlineplatform.admin.db.repo.StudentRepo;
import com.onlineplatform.admin.service.CourseService;


@Service
@Transactional
public class CouseServiceImpl implements CourseService{
	
	private StudentRepo studentRepo;

	private InstructorRepo instructorRepo;
	
	private CourseRepo courseRepo;
	
	
	public CouseServiceImpl(StudentRepo studentRepo, InstructorRepo instructorRepo, CourseRepo courseRepo) {
		this.studentRepo = studentRepo;
		this.instructorRepo = instructorRepo;
		this.courseRepo = courseRepo;
	}

	@Override
	public Course LoadCourseById(Long courseId) {
		return courseRepo.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course with id : " + courseId + "not found in database."));
	}

	@Override
	public Course createCourse(String courseName, String courseDuration, String courseDescription, Long instructorId) {
		Instructor instructor = instructorRepo.findById(instructorId).orElseThrow(() -> new EntityNotFoundException("Instructor with id : " + instructorId + "not found in database."));
		return courseRepo.save(new Course(courseName, courseDuration, courseDescription, instructor));
	}

	@Override
	public Course createOrUpdateCourse(Course course) {
		return courseRepo.save(course);
	}

	@Override
	public List<Course> findCourseByCouseName(String keyword) {
		return courseRepo.findCoursesByCourseNameContains(keyword);
	}

	@Override
	public void assignStudenttoCourse(Long courseId, Long studentId) {
		Student student = studentRepo.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student with id: " + studentId +" not found in database."));
		Course course = courseRepo.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Student with id: " + courseId +" not found in database."));
		course.assignStudentToCourse(student);
	}

	@Override
	public List<Course> fetchAll() {
		return courseRepo.findAll();
	}

	@Override
	public List<Course> fetchCousesForStudent(Long studentId) {
		return courseRepo.getCoursesByStudentId(studentId);
	}

	@Override
	public void removeCourse(Long courseId) {
		courseRepo.deleteById(courseId);
	}

}
