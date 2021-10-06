package com.example.userdetails.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.userdetails.entities.CompositeKey;
import com.example.userdetails.entities.UserRoles;

public interface UserRolesRepository extends CrudRepository<UserRoles, CompositeKey>{
	

}
