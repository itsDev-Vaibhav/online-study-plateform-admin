package com.onlineplatform.admin.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onlineplatform.admin.db.entities.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
	
	@Query(value = "select s from Student as s where s.firstName like %:name% or s.lastName like %:name%")
	List<Student> findStudentsByName(@Param("name") String name);
	
	
	
	@Query(value = "select s from Student as s where s.user.email = :email")
	Student findStrudentByEmail(@Param("email") String email);

}
