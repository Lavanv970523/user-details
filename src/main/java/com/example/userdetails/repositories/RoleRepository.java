package com.example.userdetails.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.userdetails.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>{

}
