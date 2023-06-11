package com.onlineplatform.admin.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.onlineplatform.admin.db.entities.Role;
import com.onlineplatform.admin.db.entities.User;
import com.onlineplatform.admin.db.repo.RoleRepo;
import com.onlineplatform.admin.db.repo.UserRepo;
import com.onlineplatform.admin.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	private UserRepo userRepo;
	
	private RoleRepo roleRepo;

	public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}

	@Override
	public User loadUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public User createUser(String email, String password) {
		return userRepo.save(new User(email, password));
	}

	@Override
	public void assignRoleToUser(String email, String roleName) {
		User user = loadUserByEmail(email);
		Role role = roleRepo.findByRoleName(roleName);
		user.assignRoleToUser(role);
	}

}
