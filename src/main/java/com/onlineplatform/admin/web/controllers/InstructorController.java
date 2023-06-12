package com.onlineplatform.admin.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineplatform.admin.db.entities.Instructor;
import com.onlineplatform.admin.db.entities.User;
import com.onlineplatform.admin.service.InstructorService;
import com.onlineplatform.admin.service.UserService;

@Controller
@RequestMapping(value = "/instructors")
public class InstructorController {
	
	@Autowired
	private InstructorService instructorService;
	
	private UserService userService;
	
	
	
	public InstructorController(InstructorService instructorService, UserService userService) {
		this.instructorService = instructorService;
		this.userService = userService;
	}



	@GetMapping(value = "/index")
	public String instructors(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		List<Instructor> instructors = instructorService.findInstructorsByName(keyword);
		model.addAttribute("listInstructors", instructors);
		model.addAttribute("keyword", keyword);
		return "instructor-views/instructors";
	}
	
	
	
	@GetMapping(value = "/delete")
	public String deleteInstructor(Long instructorId, String keyword) {
		instructorService.removeInstructor(instructorId);
		return "redirect:/instructors/index?keyword="+keyword;
	}
	
	@GetMapping("/formUpdate")
	public String formUpdateInstructor(Model model, Long instructorId) {
		Instructor instructor = instructorService.loadInstructorById(instructorId);
		model.addAttribute("instructor", instructor);
		return "instructor-views/formUpdate";
	}
	
	
	@PostMapping(value = "/update")
	public String updateInstructor(Instructor instructor) {
		instructorService.updateInstructor(instructor);
		return "redirect:/instructors/index";
	}
	
	@GetMapping("/formCreate")
	public String formInstructor(Model model) {
		model.addAttribute("instructor", new Instructor());
		return "instructor-views/formCreate";
	}
	
	
	@PostMapping(value = "/save")
	public String saveCourse(@Valid Instructor instructor, BindingResult bindingResult) {
		User user = userService.loadUserByEmail(instructor.getUser().getEmail());
		if (user != null) 
			bindingResult.rejectValue("user.email", null, "There is already a user is registered with same email: " + user.getEmail());
		if (bindingResult.hasErrors()) 
			return "instructor-views/formCreate";
		instructorService.createInstructor(instructor.getFirstName(), instructor.getLastName(), instructor.getSummery(), instructor.getUser().getEmail(), instructor.getUser().getPassword());
		return "redirect:/instructors/index";
	}
	
}
