package com.example.userdetails.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.userdetails.entities.Role;
import com.example.userdetails.entities.User;
import com.example.userdetails.entities.UserRoles;
import com.example.userdetails.exceptions.DaoException;
import com.example.userdetails.idao.UserDaoInterface;
import com.example.userdetails.repositories.UserRepository;
import com.example.userdetails.repositories.UserRolesRepository;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class UserDao implements UserDaoInterface {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRolesRepository userRolesRepository;

	@Override
	public List<User> getUsers() {
		
		try {
			log.info("Entered into UserDao :: getUsers");
			List<User> users = new ArrayList<>();
			userRepository.findAll().forEach(users::add);
			return users;

		} catch (Exception e) {
			log.error("Error while fetching user details", e);
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void addUser(User user) {
		try {
			log.info("Entered into UserDao :: addUser");
			userRepository.save(user);

		} catch (Exception e) {
			log.error("Error while adding user details", e);
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void updateUser(User user) {
		try {
			log.info("Entered into UserDao :: updateUser");
			userRepository.save(user);

		} catch (Exception e) {
			log.error("Error while updating user details", e);
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public User getUser(Integer id) {
		try {
			log.info("Entered into UserDao :: getUser");
			return userRepository.findById(id).get();

		} catch (Exception e) {
			log.error("Error while fetching single user details", e);
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void deleteUser(Integer id) {
		try {
			log.info("Entered into UserDao :: deleteUser");
			userRepository.deleteById(id);

		} catch (Exception e) {
			log.error("Error while deleting user details", e);
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List<Role> getRolesOfUser(Integer id) {
		try {
			log.info("Entered into UserDao :: getRolesOfUser");
			return userRepository.findUserRoles(id);

		} catch (Exception e) {
			log.error("Error while getting roles of a user", e);
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void assignRoles(UserRoles userRoles) {
		try {
			log.info("Entered into UserDao :: assignRoles");
			userRolesRepository.save(userRoles);
		} catch (Exception e) {
			log.error("Error while assigning role to a user", e);
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void removeRoles(UserRoles userRoles) {
		try {
			log.info("Entered into UserDao :: removeRoles");
			userRolesRepository.delete(userRoles);
		} catch (Exception e) {
			log.error("Error while removing role to a user", e);
			throw new DaoException(e.getMessage());
		}
		
	}

}
