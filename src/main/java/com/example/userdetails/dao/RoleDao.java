package com.example.userdetails.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.userdetails.entities.Role;
import com.example.userdetails.exceptions.DaoException;
import com.example.userdetails.idao.RoleDaoInterface;
import com.example.userdetails.repositories.RoleRepository;

@Repository
public class RoleDao implements RoleDaoInterface{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Role> getRoles() {
		try {
			return (List<Role>) roleRepository.findAll();
			
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public Role addRole(Role role) {
		try {
			return roleRepository.save(role);
			
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public Role updateRole(Role role) {
		try {
			return roleRepository.save(role);
			
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}
	
	

}
