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

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("users")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	@PostMapping(value = "users") 
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addUser(@Valid @RequestBody UserDto userDto) {
		userService.addUser(userDto);
	}

	@PutMapping("users")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void updateUser(@RequestBody UserDto userDto) {
		System.out.println("Update user constroller:: Entered");
		userService.updateUser(userDto);
	}

	@GetMapping("users/{id}")
	public User getUser(@PathVariable Integer id) {
		return userService.getUser(id);
	}

	@DeleteMapping("users/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}

	@GetMapping("users/{id}/roles")
	public List<Role> getRolesOfUser(@PathVariable Integer id) {
		return userService.getRolesOfUser(id);
	}

	@PostMapping("users/user/{userId}/role/{roleId}")
	public void assignRoles(@PathVariable Integer userId, @PathVariable Integer roleId) {
		userService.assignRoles(userId, roleId);
	}
	
	@DeleteMapping("users/user/{userId}/role/{roleId}")
	public void removeRoles(@PathVariable Integer userId, @PathVariable Integer roleId) {
		userService.removeRoles(userId, roleId);
	}

}
