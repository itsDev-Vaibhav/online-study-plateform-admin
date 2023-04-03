package com.onlineplatform.admin.db.entities;

import java.util.Objects;

public class Users {
	
	
	private Long userId;
	private String email;
	private String password;
	
	
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
		Users other = (Users) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(userId, other.userId);
	}

}
