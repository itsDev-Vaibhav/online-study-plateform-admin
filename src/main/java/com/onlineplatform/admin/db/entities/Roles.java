package com.onlineplatform.admin.db.entities;

import java.util.Objects;

public class Roles {

	private Long roleId;
	private String roleName;
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(roleId, roleName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Roles other = (Roles) obj;
		return Objects.equals(roleId, other.roleId) && Objects.equals(roleName, other.roleName);
	}
	
	
	
}
