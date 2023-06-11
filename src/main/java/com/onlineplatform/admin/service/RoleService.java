package com.onlineplatform.admin.service;

import com.onlineplatform.admin.db.entities.Role;

public interface RoleService {
	
	Role loadRoleByName(String roleName);
	
	Role createRole(String roleName);

}
