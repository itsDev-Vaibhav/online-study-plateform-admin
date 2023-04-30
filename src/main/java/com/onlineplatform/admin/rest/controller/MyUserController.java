package com.onlineplatform.admin.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineplatform.admin.db.entities.MyUser;
import com.onlineplatform.admin.db.repo.MyUserRepo;
import com.onlineplatform.admin.rest.dto.UserRequest;


@RestController
@RequestMapping("/project/v1")
public class MyUserController {
	
	@Autowired
	private MyUserRepo repo;
	
	@PostMapping("/users")
	public ResponseEntity<MyUser> createUser(@RequestBody UserRequest request) {
		System.out.println("request is coming to createUser: " +request);
		MyUser user = new MyUser();
		user.setName(request.getName());
		user.setAddress(request.getAddress());
		MyUser save = repo.save(user);
		return new ResponseEntity<MyUser>(save, HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/users")
	public ResponseEntity<List<MyUser>> getUsers() {
		System.out.println("request is coming to getUsers");
		List<MyUser> findAll = repo.findAll();
		return new ResponseEntity<List<MyUser>>(findAll, HttpStatus.OK);
	}
	
	
	@GetMapping("/user/{id}")
	public ResponseEntity<MyUser> getUser(@PathVariable Integer id) {
		System.out.println("request is coming to getUser: " +id);
		Optional<MyUser> findById = repo.findById(id);
		return new ResponseEntity<MyUser>(findById.get(), HttpStatus.OK);
	}
}
