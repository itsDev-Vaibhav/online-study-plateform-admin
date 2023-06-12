package com.onlineplatform.admin.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	
	@GetMapping(value = "/")
	public String appValue() {
		return "redirect:/courses/index";
	}

}
