package com.onlineplatform.admin.db.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id", nullable =  false)
	private Long studentId;
	
	@Basic
	@Column(name = "first_name", nullable =  false, length = 45)
	private String firstName;
	
	@Basic
	@Column(name = "last_name", nullable =  false, length = 45)
	private String lastName;
	
	@Basic
	@Column(name = "level", nullable =  false, length = 64)
	private String level;
	
	@Basic
	@Column(nullable = false)
	private Boolean isDeleted;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_DT", updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate; 
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	
	@Column(name = "UPDATED_DT", insertable = false)
	@UpdateTimestamp
	private LocalDateTime updatedDate;

	@ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
	private Set<Course> courses = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	private User user;
	
	

	public Student() {
	}

	public Student(String firstName, String lastName, String level, User user) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.level = level;
		this.isDeleted = false;
		this.createdBy = "System";
		this.updatedBy = "System";
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdBy, createdDate, firstName, isDeleted, lastName, level, studentId, updatedBy,
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
		Student other = (Student) obj;
		return Objects.equals(createdBy, other.createdBy) && Objects.equals(createdDate, other.createdDate)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(isDeleted, other.isDeleted)
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


	public Boolean getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public String getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}


	public Set<Course> getCourses() {
		return courses;
	}


	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Students [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", level="
				+ level + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}
	
}
