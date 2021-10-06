package com.example.userdetails.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userdetails.entities.Role;
import com.example.userdetails.exceptions.DelegationException;
import com.example.userdetails.idao.RoleDaoInterface;

@Service
public class RoleService {
	
	@Autowired
	private RoleDaoInterface roleDao;

	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		try {
			return roleDao.getRoles();
		} catch (Exception e) {
			throw new DelegationException(e.getMessage());
		}
		
		
	}

	public void addRole(Role role) {
		try {
			role.setCreatedOn(new Date());
			
		} catch (Exception e) {
			throw new DelegationException(e.getMessage());
		}
		roleDao.addRole(role);
	}

	public void updateRole(Role role) {
		try {
			role.setModifiedOn(new Date());
			
		} catch (Exception e) {
			throw new DelegationException(e.getMessage());
		}
		roleDao.updateRole(role);
	}

}
