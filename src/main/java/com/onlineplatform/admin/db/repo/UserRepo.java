package com.onlineplatform.admin.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineplatform.admin.db.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
}
