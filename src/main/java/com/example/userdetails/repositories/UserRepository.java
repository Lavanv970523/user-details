package com.example.userdetails.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.userdetails.entities.Role;
import com.example.userdetails.entities.User;

public interface UserRepository extends CrudRepository<User, Integer>{

//	@Query("SELECT roles.role_id, name, description, created_date, modified_date from user_roles join roles on user_roles.role_id=roles.role_id\r\n"
//			+ "where user_roles.user_id=1")
	@Query("SELECT roles from User u where u.id=:id")
	List<Role> findUserRoles(@Param("id") Integer id);
	
}
