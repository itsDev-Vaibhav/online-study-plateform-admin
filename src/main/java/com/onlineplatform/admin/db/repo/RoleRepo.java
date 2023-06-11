package com.onlineplatform.admin.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineplatform.admin.db.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	
	Role findByRoleName(String roleName);

}
