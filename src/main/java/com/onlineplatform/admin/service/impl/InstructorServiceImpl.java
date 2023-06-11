package com.onlineplatform.admin.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.onlineplatform.admin.db.entities.Course;
import com.onlineplatform.admin.db.entities.Instructor;
import com.onlineplatform.admin.db.entities.User;
import com.onlineplatform.admin.db.repo.InstructorRepo;
import com.onlineplatform.admin.service.CourseService;
import com.onlineplatform.admin.service.InstructorService;
import com.onlineplatform.admin.service.UserService;


@Service
@Transactional
public class InstructorServiceImpl implements InstructorService{
	
	
	private InstructorRepo instructorRepo;
	
	private CourseService courseService;
	
	private UserService userService;
	
	
	public InstructorServiceImpl(InstructorRepo instructorRepo, CourseService courseService, UserService userService) {
		this.instructorRepo = instructorRepo;
		this.courseService = courseService;
		this.userService = userService;
	}

	@Override
	public Instructor loadInstructorById(Long instructorId) {
		return instructorRepo.findById(instructorId).orElseThrow(() -> new EntityNotFoundException("Instructor with id: " + instructorId +"not found."));
	}

	@Override
	public List<Instructor> findInstructorsByName(String name) {
		return instructorRepo.findByName(name);
	}

	@Override
	public Instructor loadInstructorByEmail(String email) {
		return instructorRepo.findInstructorByEmail(email);
	}

	@Override
	public Instructor createInstructor(String firstName, String lastName, String summery, String email,
			String password) {
		User user = userService.createUser(email, password);
		userService.assignRoleToUser(email, "Instructor");
		return instructorRepo.save(new Instructor(firstName, lastName, summery, user));
	}

	@Override
	public Instructor updateInstructor(Instructor instructor) {
		return instructorRepo.save(instructor);
	}

	@Override
	public List<Instructor> fetchAllInstructors() {
		return instructorRepo.findAll();
	}

	@Override
	public void removeInstructor(Long instructorId) {
		Instructor instructor = loadInstructorById(instructorId);
		for(Course course : instructor.getCourses()) {
			courseService.removeCourse(course.getCourseId());
		}
		instructorRepo.deleteById(instructorId);
	}

}
