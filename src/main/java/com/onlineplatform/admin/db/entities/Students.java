package com.onlineplatform.admin.db.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Students {
	
	
	private Long studentId;
	private String firstName;
	private String lastName;
	private String level;
	private Boolean isActive;
//	@Column(name = "CREATED_BY")
	private String createdBy;
	
//	@Column(name = "CREATED_DT", updatable = false)
//	@CreationTimestamp
	private LocalDate createdDate; 
	
//	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	
//	@Column(name = "UPDATED_DT", insertable = false)
//	@UpdateTimestamp
	private LocalDate updatedDate;

	
	private Set<Courses> courses = new HashSet<>();
	
	
	private Users user;
	
	

	public Students() {
	}

	public Students(String firstName, String lastName, String level, Boolean isActive, String createdBy,
			LocalDate createdDate, String updatedBy, LocalDate updatedDate, Users user) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.level = level;
		this.isActive = isActive;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdBy, createdDate, firstName, isActive, lastName, level, studentId, updatedBy,
				updatedDate);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Students other = (Students) obj;
		return Objects.equals(createdBy, other.createdBy) && Objects.equals(createdDate, other.createdDate)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(isActive, other.isActive)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(level, other.level)
				&& Objects.equals(studentId, other.studentId) && Objects.equals(updatedBy, other.updatedBy)
				&& Objects.equals(updatedDate, other.updatedDate);
	}


	public Long getStudentId() {
		return studentId;
	}


	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}


	public Boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public LocalDate getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}


	public String getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	public LocalDate getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}


	public Set<Courses> getCourses() {
		return courses;
	}


	public void setCourses(Set<Courses> courses) {
		this.courses = courses;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Students [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", level="
				+ level + ", isActive=" + isActive + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}
	
}
