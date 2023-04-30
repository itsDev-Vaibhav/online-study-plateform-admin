package com.onlineplatform.admin.db.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineplatform.admin.db.entities.MyUser;

public interface MyUserRepo extends JpaRepository<MyUser, Serializable> {

}
