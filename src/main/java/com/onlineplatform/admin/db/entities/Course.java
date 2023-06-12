package com.onlineplatform.admin.db.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id", nullable = false)
	private Long courseId;
	
	@Basic
	@Column(name = "course_name", nullable = false, length = 45)
	private String courseName;
	
	@Basic
	@Column(name = "course_duration", nullable = false, length = 45)
	private String courseDuration;
	

	@Basic
	@Column(name = "course_description", nullable = false, length = 64)
	private String courseDescription;
	
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
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instructor_id", referencedColumnName = "instructor_id", nullable = false)
	private Instructor instructor;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "enrolled_in", joinColumns = {@JoinColumn(name = "course_id")},
	inverseJoinColumns = {@JoinColumn(name = "student_id")})
	private Set<Student> students = new HashSet<>();
	
	
	public void assignStudentToCourse(Student s) {
		this.students.add(s);
		s.getCourses().add(this);
	}
	
	public void removeStudentFromCourse(Student s) {
		this.students.remove(s);
		s.getCourses().remove(this);
	}
	
	public Course() {

	}
	
	public Course(String courseName, String courseDuration, String courseDescription, Instructor instructor) {
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.courseDescription = courseDescription;
		this.isDeleted = false;
		this.createdBy = "System";
		this.updatedBy = "System";
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
		Course other = (Course) obj;
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


	public Instructor getInstructor() {
		return instructor;
	}


	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}


	public Set<Student> getStudents() {
		return students;
	}


	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Courses [courseName=" + courseName + ", courseDuration=" + courseDuration + ", courseDescription="
				+ courseDescription + ", isDeleted=" + isDeleted + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}
}
