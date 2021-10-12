package com.example.userdetails.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.example.userdetails.dto.UserDto;
import com.example.userdetails.entities.Role;
import com.example.userdetails.entities.User;
import com.example.userdetails.entities.UserRoles;
import com.example.userdetails.exceptions.DelegationException;
import com.example.userdetails.idao.UserDaoInterface;

import lombok.extern.log4j.Log4j2;

@Service
@Validated
@Log4j2
public class UserService {

	@Autowired
	private UserDaoInterface userDao;

	@Autowired
	private ModelMapper mapper;

	public Map<String, Object> getUsers(Integer pageNo, Integer pageSize) {
		log.info("Entered into UserService :: getUsers");
		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);
			Page<User> pageResult = userDao.getUsers(paging);
			Map<String, Object> result = new HashMap<>();
			result.put("users", pageResult.getContent());
			result.put("totalPages", pageResult.getTotalPages());
			result.put("currentPage", pageResult.getNumber());
			result.put("totalUsers", pageResult.getTotalElements());
			return result;
		} catch (Exception e) {
			log.error("Error while fetching user details", e);
			throw new DelegationException(e.getMessage());
		}
	}

	@Transactional
	public User addUser(UserDto userDto) {
		try {
			log.info("Entered into UserService :: addUser");
			User user = mapper.map(userDto, User.class);
			log.debug("Mapped dto to user object");
			user.setCreatedOn(new Date());
			Role role = new Role();
			role.setRoleId(6);
			List<Role> list = new ArrayList<>();
			list.add(role);
			user.setRoles(list);
			log.debug("Default role assigned to User");
			return userDao.addUser(user);

		} catch (Exception e) {
			log.error("Error while adding user details", e);
			throw new DelegationException(e.getMessage());
		}
	}

	@Transactional
	public User updateUser(UserDto userDto) {
		try {
			log.info("Entered into UserService :: updateUser");
			User user = mapper.map(userDto, User.class);
			user.setModifiedOn(new Date());
			return userDao.updateUser(user);
		} catch (Exception e) {
			log.error("Error while updating user details", e);
			throw new DelegationException(e.getMessage());
		}
	}

	public User getUser(Integer id) {
		try {
			log.info("Entered into UserService :: getuser");
			return userDao.getUser(id);
		} catch (Exception e) {
			log.error("Error while fetching single user details", e);
			throw new DelegationException(e.getMessage());
		}
	}

	@Transactional
	public void deleteUser(Integer id) {
		try {
			log.info("Entered into UserService :: deleteUser");
			userDao.deleteUser(id);
		} catch (Exception e) {
			log.error("Error while deleting user details", e);
			throw new DelegationException(e.getMessage());
		}
	}

	public List<Role> getRolesOfUser(Integer id) {
		try {
			log.info("Entered into UserService :: getRolesOfUser");
			return userDao.getRolesOfUser(id);
		} catch (Exception e) {
			log.error("Error while getting roles of a user", e);
			throw new DelegationException(e.getMessage());
		}
	}

	public void assignRoles(Integer userId, Integer roleId) {
		try {
			log.info("Entered into UserService :: assignRoles");
			UserRoles userRoles = new UserRoles();
			userRoles.setUserId(userId);
			userRoles.setRoleId(roleId);
			log.debug("User Role id's are set");
			userDao.assignRoles(userRoles);
		} catch (Exception e) {
			log.error("Error while assigning role to a user", e);
			throw new DelegationException(e.getMessage());
		}
	}

	@Transactional
	public void removeRoles(Integer userId, Integer roleId) {
		try {
			log.info("Entered into UserService :: removeRoles");
			UserRoles userRoles = new UserRoles();
			userRoles.setUserId(userId);
			userRoles.setRoleId(roleId);
			log.debug("User Role id's are set");
			userDao.removeRoles(userRoles);
		} catch (Exception e) {
			log.error("Error while removing role to a user", e);
			throw new DelegationException(e.getMessage());
		}

	}

}
