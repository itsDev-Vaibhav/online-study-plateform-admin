package com.onlineplatform.admin.service;

import java.util.List;

import com.onlineplatform.admin.db.entities.Instructor;

public interface InstructorService {
		
	
	Instructor loadInstructorById(Long instructorId);
	
	List<Instructor> findInstructorsByName(String name);
	
	Instructor loadInstructorByEmail(String email);
	
	Instructor createInstructor(String firstName, String lastName, String summery, String email, String password);
	
	Instructor updateInstructor(Instructor instructor);
	
	List<Instructor> fetchAllInstructors();
	
	void removeInstructor(Long instructorId);
}
