package com.onlineplatform.admin.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project/v1")
public class AdminController {
	
	
	@GetMapping("/getMsg")
	public ResponseEntity<String> showMessage() {
		System.out.println("call is coming to showMessage().");
		return new ResponseEntity<String>("Hi this is response from admin Controller.", HttpStatus.OK);
	}

}
