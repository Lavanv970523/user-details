package com.example.userdetails.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.userdetails.dto.RoleDto;
import com.example.userdetails.entities.Role;
import com.example.userdetails.exceptions.DaoException;
import com.example.userdetails.exceptions.DelegationException;
import com.example.userdetails.idao.RoleDaoInterface;

@Service
public class RoleService {
	
	@Autowired
	private RoleDaoInterface roleDao;
	
	@Autowired
	private ModelMapper mapper;

	public List<Role> getRoles() {
		try {
			return roleDao.getRoles();
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
		
		
	}

	public Role addRole(RoleDto roleDto) {
		try {
			Role role= mapper.map(roleDto, Role.class);
			role.setCreatedOn(new Date());
			return roleDao.addRole(role);
			
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	public Role updateRole(RoleDto roleDto) {
		try {
			Role role= mapper.map(roleDto, Role.class);
			role.setModifiedOn(new Date());
			return roleDao.updateRole(role);
			
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

}
