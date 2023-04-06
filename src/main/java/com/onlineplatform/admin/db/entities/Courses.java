package com.onlineplatform.admin.db.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Courses {
	
	private Long courseId;
	private String courseName;
	private String courseDuration;
	private String courseDescription;
	private Boolean isDeleted;
	
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
	
	private Instructor instructor;
	
	private Set<Students> students = new HashSet<>();
	
	
	public void assignStudentToCourse(Students s) {
		this.students.add(s);
	}
	
	public Courses() {

	}
	
	public Courses(String courseName, String courseDuration, String courseDescription, Boolean isDeleted,
			String createdBy, LocalDate createdDate, String updatedBy, LocalDate updatedDate, Instructor instructor) {
		super();
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.courseDescription = courseDescription;
		this.isDeleted = isDeleted;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.instructor = instructor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseDescription, courseDuration, courseId, courseName, createdBy, createdDate, isDeleted,
				updatedBy, updatedDate);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Courses other = (Courses) obj;
		return Objects.equals(courseDescription, other.courseDescription)
				&& Objects.equals(courseDuration, other.courseDuration) && Objects.equals(courseId, other.courseId)
				&& Objects.equals(courseName, other.courseName) && Objects.equals(createdBy, other.createdBy)
				&& Objects.equals(createdDate, other.createdDate) && Objects.equals(isDeleted, other.isDeleted)
				&& Objects.equals(updatedBy, other.updatedBy) && Objects.equals(updatedDate, other.updatedDate);
	}


	public Long getCourseId() {
		return courseId;
	}


	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getCourseDuration() {
		return courseDuration;
	}


	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}


	public String getCourseDescription() {
		return courseDescription;
	}


	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
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


	public Instructor getInstructor() {
		return instructor;
	}


	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}


	public Set<Students> getStudents() {
		return students;
	}


	public void setStudents(Set<Students> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Courses [courseName=" + courseName + ", courseDuration=" + courseDuration + ", courseDescription="
				+ courseDescription + ", isDeleted=" + isDeleted + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}
}
