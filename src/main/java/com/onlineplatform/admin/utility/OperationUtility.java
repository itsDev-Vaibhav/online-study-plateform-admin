package com.onlineplatform.admin.utility;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.onlineplatform.admin.db.entities.Role;
import com.onlineplatform.admin.db.entities.User;
import com.onlineplatform.admin.db.repo.RoleRepo;
import com.onlineplatform.admin.db.repo.UserRepo;

public class OperationUtility {
	
	
	public static void userOperations(UserRepo urepo) {
		createUsers(urepo);
//		updateUsers(urepo);
//		deleteUser(urepo);
//		fetchUser(urepo);
	}
	
	public static void roleOperations(RoleRepo rrepo) {
		createRole(rrepo);
//		updateRole(rrepo);
//		deleteRole(rrepo);
//		fetchRole(rrepo);
	}
	
	
	public static void assignRoleToUSers(UserRepo userRepo, RoleRepo roleRepo) {
		Role role = roleRepo.findByRoleName("Admin");
		if (role == null) throw new EntityNotFoundException("Role Not Found");
		List<User> users = userRepo.findAll();
		users.forEach(user -> {
			user.assignRoleToUser(role);
			userRepo.save(user);
		});
	}

	private static void fetchRole(RoleRepo rrepo) {
		rrepo.findAll().forEach(role -> System.out.println(role.toString()));
	}

	private static void deleteRole(RoleRepo rrepo) {
		Role role = rrepo.findById(2l).orElseThrow(() -> new EntityNotFoundException("Role not found!"));
		rrepo.delete(role);
		
	}

	private static void updateRole(RoleRepo rrepo) {
		Role role = rrepo.findById(2l).orElseThrow(() -> new EntityNotFoundException("Role not found!"));
		role.setRoleName("newAdmin");
		rrepo.save(role);
	}

	private static void createRole(RoleRepo rrepo) {
		Role role1 = new Role("Admin");
		rrepo.save(role1);
		
		Role role2 = new Role("Instructor");
		rrepo.save(role2);
		
		Role role3 = new Role("Student");
		rrepo.save(role3);
	}

	private static void createUsers(UserRepo urepo) {
		// TODO Auto-generated method stub
		User user1 = new User("user1@gmail.com", "pass1");
		urepo.save(user1);
		User user2 = new User("user2@gmail.com", "pass2");
		urepo.save(user2);
		User user3 = new User("user3@gmail.com", "pass3");
		urepo.save(user3);
		User user4 = new User("user4@gmail.com", "pass4");
		urepo.save(user4);
	}
		
	private static void updateUsers(UserRepo urepo) {
		User user = urepo.findById(2l).orElseThrow(() -> new EntityNotFoundException("User not found!"));
		user.setEmail("updatedMail@gmail.com");
		urepo.save(user);
	}
	
	private static void deleteUser(UserRepo urepo) {
		User user = urepo.findById(3l).orElseThrow(() -> new EntityNotFoundException("User not found!"));
		urepo.delete(user);
	}
	
	private static void fetchUser(UserRepo urepo) {
		urepo.findAll().forEach(user -> System.out.println(user.toString()));
	}
	

}
