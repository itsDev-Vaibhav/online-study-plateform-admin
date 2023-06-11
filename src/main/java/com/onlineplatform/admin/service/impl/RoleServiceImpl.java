package com.onlineplatform.admin.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineplatform.admin.db.entities.Role;
import com.onlineplatform.admin.db.repo.RoleRepo;
import com.onlineplatform.admin.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public Role loadRoleByName(String roleName) {
		return roleRepo.findByRoleName(roleName);
	}

	@Override
	public Role createRole(String roleName) {
		return roleRepo.save(new Role(roleName));
	}

}
