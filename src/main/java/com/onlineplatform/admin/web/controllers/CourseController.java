package com.onlineplatform.admin.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineplatform.admin.db.entities.Course;
import com.onlineplatform.admin.db.entities.Instructor;
import com.onlineplatform.admin.service.CourseService;
import com.onlineplatform.admin.service.InstructorService;

@Controller
@RequestMapping(value = "/courses")
public class CourseController {
	
	private CourseService courseService;
	
	private InstructorService instructorService;

	public CourseController(CourseService courseService, InstructorService instructorService) {
		this.courseService = courseService;
		this.instructorService = instructorService;
	}



	@GetMapping(value = "/index")
	public String courses(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
		List<Course> findCourseByCouseName = courseService.findCourseByCouseName(keyword);
		model.addAttribute("listCourses", findCourseByCouseName);
		model.addAttribute("keyword", keyword);
		return "course-views/courses";
	} 
	
	
	
	@GetMapping(value = "/delete")
	public String deleteCourse(Long courseId, String keyword) {
		courseService.removeCourse(courseId);
		return "redirect:/courses/index?keyword="+keyword;
	}
	
	
	@GetMapping("/formUpdate")
	public String formUpdate(Model model, Long courseId) {
		Course course = courseService.LoadCourseById(courseId);
		List<Instructor> instructors = instructorService.fetchAllInstructors();
		model.addAttribute("course", course);
		model.addAttribute("listInstructors", instructors);
		return "course-views/formUpdate";
	}
	
	
	@PostMapping(value = "/save")
	public String save(Course course) {
		courseService.createOrUpdateCourse(course);
		return "redirect:/courses/index";
	}
	
	
	@PostMapping(value = "/saveCourse")
	public String saveCourse(Course course) {
		courseService.createCourse(course.getCourseName(), course.getCourseDuration(), course.getCourseDescription(), course.getInstructor().getInstructorId());
		return "redirect:/courses/index";
	}
	
	
	
	@GetMapping(value = "/formCreate")
	public String createCourses(Model model) {
		List<Instructor> instructors = instructorService.fetchAllInstructors();
		model.addAttribute("listInstructors", instructors);
		model.addAttribute("course", new Course());
		return "course-views/formCreate";
	}
	
	
	@GetMapping(value = "/index/student")
	public String coursesForCurrentStudent(Model model) {
		Long studentId = 1L;
		List<Course> subscribedCourses = courseService.fetchCousesForStudent(studentId);
		List<Course> otherCourses = courseService.fetchAll().stream().filter(course -> !subscribedCourses.contains(course)).collect(Collectors.toList());
		model.addAttribute("listCourses", subscribedCourses);
		model.addAttribute("otherCourses", otherCourses);
		return "course-views/student-courses";
	}
	
	@GetMapping(value = "/enrollStudent")
	public String enrollStudentIntoCourse(Long courseId) {
		Long studentId = 1L;
		courseService.assignStudenttoCourse(courseId, studentId);
		return "redirect:/courses/index/student";
	}
	
	
	@GetMapping(value = "/index/instructor")
	public String currentInstructorCourses(Model model) {
		Long instructorId = 1L;
		Instructor instructor = instructorService.loadInstructorById(instructorId);
		model.addAttribute("listCourses", instructor.getCourses());
		return "course-views/instructor-courses";
	}
	
	@GetMapping(value = "/instructor")
	public String coursesByInstructorId(Model model, Long instructorId) {
		Instructor instructor = instructorService.loadInstructorById(instructorId);
		model.addAttribute("listCourses", instructor.getCourses());
		return "course-views/instructor-courses";
	}

}
