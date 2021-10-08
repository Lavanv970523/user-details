package com.example.userdetails.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.userdetails.entities.CompositeKey;
import com.example.userdetails.entities.UserRoles;

public interface UserRolesRepository extends CrudRepository<UserRoles, CompositeKey>{
	

}
