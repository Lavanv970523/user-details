package com.example.userdetails.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.userdetails.dto.UserDto;
import com.example.userdetails.entities.Role;
import com.example.userdetails.entities.User;
import com.example.userdetails.entities.UserRoles;
import com.example.userdetails.exceptions.DelegationException;
import com.example.userdetails.idao.UserDaoInterface;

@Service
@Validated
public class UserService {

	@Autowired
	private UserDaoInterface userDao;

	@Autowired
	private ModelMapper mapper;

	public List<User> getUsers() {
		try {

			return userDao.getUsers();
		} catch (Exception e) {
			throw new DelegationException(e.getMessage());
		}
	}

	public void addUser(UserDto userDto) {
		try {

			User user= mapper.map(userDto, User.class);
			user.setCreatedOn(new Date());
			Role role = new Role();
			role.setRoleId(6);
			List<Role> list = new ArrayList<>();
			list.add(role);
			user.setRoles(list);
			userDao.addUser(user);

		} catch (Exception e) {
			// TODO: handle exception
			throw new DelegationException(e.getMessage());
		}
	}

	public void updateUser(UserDto userDto) {
		try {
			
			User user=mapper.map(userDto, User.class);
			user.setModifiedOn(new Date());
			userDao.updateUser(user);
		} catch (Exception e) {
			// TODO: handle exception
			throw new DelegationException(e.getMessage());
		}
	}

	public User getUser(Integer id) {
		try {

			return userDao.getUser(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new DelegationException(e.getMessage());
		}
	}

	public void deleteUser(Integer id) {
		try {

			userDao.deleteUser(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new DelegationException(e.getMessage());
		}
	}

	public List<Role> getRolesOfUser(Integer id) {
		try {

			return userDao.getRolesOfUser(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new DelegationException(e.getMessage());
		}
	}

	public void assignRoles(Integer userId, Integer roleId) {
		// TODO Auto-generated method stub
		try {

			UserRoles userRoles = new UserRoles();
			userRoles.setUserId(userId);
			userRoles.setRoleId(roleId);
			userDao.assignRoles(userRoles);
		} catch (Exception e) {
			// TODO: handle exception
			throw new DelegationException(e.getMessage());
		}
	}

	public void removeRoles(Integer userId, Integer roleId) {
		try {

			UserRoles userRoles = new UserRoles();
			userRoles.setUserId(userId);
			userRoles.setRoleId(roleId);
			userDao.removeRoles(userRoles);
		} catch (Exception e) {
			// TODO: handle exception
			throw new DelegationException(e.getMessage());
		}

	}

}
