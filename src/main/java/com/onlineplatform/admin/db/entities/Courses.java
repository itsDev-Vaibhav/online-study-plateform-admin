package com.onlineplatform.admin.db.entities;

import java.time.LocalDate;
import java.util.Objects;

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


	
	

}
