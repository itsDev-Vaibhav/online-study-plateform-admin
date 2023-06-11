package com.onlineplatform.admin.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.onlineplatform.admin.db.entities.Course;
import com.onlineplatform.admin.db.entities.Student;
import com.onlineplatform.admin.db.entities.User;
import com.onlineplatform.admin.db.repo.StudentRepo;
import com.onlineplatform.admin.service.StudentService;
import com.onlineplatform.admin.service.UserService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{
	
	
	private StudentRepo studentRepo;
	
	private UserService userService;
	
	
	
	public StudentServiceImpl(StudentRepo studentRepo, UserService userService) {
		this.studentRepo = studentRepo;
		this.userService = userService;
	}

	@Override
	public Student loadStudentById(Long studentId) {
		return studentRepo.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student with id: " + studentId + "not found."));
	}

	@Override
	public List<Student> findStudentsByName(String name) {
		return studentRepo.findStudentsByName(name);
	}

	@Override
	public Student loadStudentByEmail(String email) {
		return studentRepo.findStrudentByEmail(email);
	}

	
	@Override
	public Student createStudent(String firstName, String lastName, String level, String email, String password) {
		User user = userService.createUser(email, password);
		userService.assignRoleToUser(email, "Student");
		return studentRepo.save(new Student(firstName, lastName, level, user));
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepo.save(student);
	}

	@Override
	public List<Student> fetchStudents() {
		return studentRepo.findAll();
	}

	@Override
	public void removeStudent(Long studentId) {
		Student student = loadStudentById(studentId);
		Iterator<Course> iterator = student.getCourses().iterator();
		if(iterator.hasNext()) {
			Course course = iterator.next();
			course.removeStudentFromCourse(student);
		}
		studentRepo.deleteById(studentId);
	}

}
