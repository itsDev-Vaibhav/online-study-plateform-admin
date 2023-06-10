package com.onlineplatform.admin.db.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {
	
	
	private Long userId;
	private String email;
	private String password;
	
	private Set<Role> roles = new HashSet<>();
	
	private Student student;
	
	private Instructor instructor;
	
	
	
	
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}



	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password, userId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(userId, other.userId);
	}



	@Override
	public String toString() {
		return "Users [userId=" + userId + ", email=" + email + ", password=" + password + "]";
	}
	
	
	

}
