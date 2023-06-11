package com.onlineplatform.admin.db.entities;

import java.time.LocalDate;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "instructors")
public class Instructor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "instructor_id", nullable = false)
	private Long instructorId;
	
	@Basic
	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;
	
	@Basic
	@Column(name = "last_name", nullable = false, length = 45)
	private String lastName;
	
	@Basic
	@Column(name = "summery", nullable = false, length = 64)
	private String summery;
	
	
	@Basic
	@Column(nullable = false)
	private Boolean isDeleted;
	
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "CREATED_DT", updatable = false)
	@CreationTimestamp
	private LocalDate createdDate; 
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	
	@Column(name = "UPDATED_DT", insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	@OneToMany(mappedBy =  "instructor", fetch = FetchType.LAZY)
	private Set<Course> courses = new HashSet<>();
	
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	private User user;
	
	
	public Instructor() {
	}
	
	
	public void setCoursesToInstructor(Course c) {
		this.courses.add(c);
	}
	


	public Instructor(String firstName, String lastName, String summery, User user) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.summery = summery;
		this.isDeleted = false;
		this.createdBy = "System";
		this.updatedBy = "System";
		this.user = user;
	}

	public Long getInstructorId() {
		return instructorId;
	}


	public void setInstructorId(Long instructorId) {
		this.instructorId = instructorId;
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


	public String getSummery() {
		return summery;
	}


	public void setSummery(String summery) {
		this.summery = summery;
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
	public int hashCode() {
		return Objects.hash(createdBy, createdDate, firstName, instructorId, isDeleted, lastName, summery, updatedBy,
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
		Instructor other = (Instructor) obj;
		return Objects.equals(createdBy, other.createdBy) && Objects.equals(createdDate, other.createdDate)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(instructorId, other.instructorId)
				&& Objects.equals(isDeleted, other.isDeleted) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(summery, other.summery) && Objects.equals(updatedBy, other.updatedBy)
				&& Objects.equals(updatedDate, other.updatedDate);
	}


	@Override
	public String toString() {
		return "Instructor [firstName=" + firstName + ", lastName=" + lastName + ", summery=" + summery + ", isDeleted="
				+ isDeleted + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + "]";
	}
	
	
	

}
