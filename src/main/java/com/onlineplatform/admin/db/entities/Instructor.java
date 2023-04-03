package com.onlineplatform.admin.db.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Instructor {
	
	
	private Long instructorId;
	private String firstName;
	private String lastName;
	private String summery;
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

}
