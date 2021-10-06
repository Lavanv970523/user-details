package com.example.userdetails.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.userdetails.dto.UserDto;
import com.example.userdetails.entities.Role;
import com.example.userdetails.entities.User;
import com.example.userdetails.service.UserService;

import lombok.extern.log4j.Log4j2;


@RestController
@Log4j2
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("users")
	public List<User> getUsers() {
		log.info("Entered into UserController :: getUsers");
		return userService.getUsers();
	}

	@PostMapping(value = "users") 
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addUser(@Valid @RequestBody UserDto userDto) {
		log.info("Entered into UserController :: addUser");
		userService.addUser(userDto);
	}

	@PutMapping("users")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void updateUser(@RequestBody UserDto userDto) {
		log.info("Entered into UserController :: updateUser");
		userService.updateUser(userDto);
	}

	@GetMapping("users/{id}")
	public User getUser(@PathVariable Integer id) {
		log.info("Entered into UserController :: getUser");
		return userService.getUser(id);
	}

	@DeleteMapping("users/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Integer id) {
		log.info("Entered into UserController :: deleteUser");
		userService.deleteUser(id);
	}

	@GetMapping("users/{id}/roles")
	public List<Role> getRolesOfUser(@PathVariable Integer id) {
		log.info("Entered into UserController :: getRolesOfUser");
		return userService.getRolesOfUser(id);
	}

	@PostMapping("users/user/{userId}/role/{roleId}")
	public void assignRoles(@PathVariable Integer userId, @PathVariable Integer roleId) {
		log.info("Entered into UserController :: assignRoles");
		userService.assignRoles(userId, roleId);
	}
	
	@DeleteMapping("users/user/{userId}/role/{roleId}")
	public void removeRoles(@PathVariable Integer userId, @PathVariable Integer roleId) {
		log.info("Entered into UserController :: removeRoles");
		userService.removeRoles(userId, roleId);
	}

}
