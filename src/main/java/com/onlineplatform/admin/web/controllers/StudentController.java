package com.onlineplatform.admin.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineplatform.admin.db.entities.Instructor;
import com.onlineplatform.admin.db.entities.Student;
import com.onlineplatform.admin.db.entities.User;
import com.onlineplatform.admin.service.StudentService;
import com.onlineplatform.admin.service.UserService;

@Controller
@RequestMapping(value = "/students")
public class StudentController {
	
	private StudentService studentService;
	
	private UserService userService;

	public StudentController(StudentService studentService, UserService userService) {
		this.studentService = studentService;
		this.userService = userService;
	}
	
	
	@GetMapping(value = "/index")
	public String students(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		List<Student> students = studentService.findStudentsByName(keyword);
		model.addAttribute("listStudents", students);
		model.addAttribute("keyword", keyword);
		return "student-views/students";
	}
	
	@GetMapping(value = "/delete")
	public String deleteStudent(Long studentId, String keyword) {
		studentService.removeStudent(studentId);
		return "redirect:/students/index?keyword="+keyword;
	}
	
	@GetMapping("/formUpdate")
	public String formUpdateStudent(Model model, Long studentId) {
		Student student = studentService.loadStudentById(studentId);
		model.addAttribute("student", student);
		return "student-views/formUpdate";
	}
	
	
	@PostMapping(value = "/update")
	public String updateInstructor(Student instructor) {
		studentService.updateStudent(instructor);
		return "redirect:/students/index";
	}
	
	@GetMapping("/formCreate")
	public String formStudent(Model model) {
		model.addAttribute("student", new Student());
		return "student-views/formCreate";
	}
	
	
	@PostMapping(value = "/save")
	public String saveStudent(@Valid Student student, BindingResult bindingResult) {
		User user = userService.loadUserByEmail(student.getUser().getEmail());
		if (user != null) 
			bindingResult.rejectValue("user.email", null, "There is already a user is registered with same email: " + user.getEmail());
		if (bindingResult.hasErrors()) 
			return "student-views/formCreate";
		studentService.createStudent(student.getFirstName(), student.getLastName(), student.getLevel(), student.getUser().getEmail(), student.getUser().getPassword());
		return "redirect:/students/index";
	}
	
	
	

}
