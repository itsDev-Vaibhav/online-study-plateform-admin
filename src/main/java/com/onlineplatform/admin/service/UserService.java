package com.onlineplatform.admin.service;

import com.onlineplatform.admin.db.entities.User;

public interface UserService {
	
	User loadUserByEmail(String email);
	
	User createUser(String email, String password);
	
	void assignRoleToUser(String email, String roleName);
}
