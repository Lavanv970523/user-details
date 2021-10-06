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

@Repository
public class UserDao implements UserDaoInterface {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRolesRepository userRolesRepository;

	@Override
	public List<User> getUsers() {
		try {
			List<User> users = new ArrayList<>();
			userRepository.findAll().forEach(users::add);
			return users;

		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void addUser(User user) {
		try {
			userRepository.save(user);

		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void updateUser(User user) {
		try {
			userRepository.save(user);

		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public User getUser(Integer id) {
		try {
			return userRepository.findById(id).get();

		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void deleteUser(Integer id) {
		try {
			System.out.println("Delete Dao:: entered");
			userRepository.deleteById(id);

		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List<Role> getRolesOfUser(Integer id) {
		try {
			return userRepository.findUserRoles(id);

		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void assignRoles(UserRoles userRoles) {
		try {
			userRolesRepository.save(userRoles);
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void removeRoles(UserRoles userRoles) {
		try {
			userRolesRepository.delete(userRoles);;
		} catch (Exception e) {
			throw new DaoException(e.getMessage());
		}
		
	}

}
