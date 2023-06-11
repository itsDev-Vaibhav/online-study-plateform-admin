package com.onlineplatform.admin.service;

import java.util.List;

import com.onlineplatform.admin.db.entities.Course;

public interface CourseService {
	
	Course LoadCourseById(Long courseId);
	
	Course createCourse(String courseName, String courseDuration, String courseDescription, Long instructorId);

	Course createOrUpdateCourse(Course course);
	
	List<Course> findCourseByCouseName(String keyword);
	
	void assignStudenttoCourse(Long courseId, Long studentId);
	
	List<Course> fetchAll();
	
	List<Course> fetchCousesForStudent(Long studentId);
	
	void removeCourse(Long courseId);

}
