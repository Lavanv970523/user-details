package com.example.userdetails.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.userdetails.dto.UserDto;
import com.example.userdetails.entities.Role;
import com.example.userdetails.entities.User;
import com.example.userdetails.repositories.UserPagingRepository;
import com.example.userdetails.service.UserService;

import lombok.extern.log4j.Log4j2;


@RestController
@Log4j2
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserPagingRepository userPagingRepository;

	@GetMapping("users")
	public ResponseEntity<Map<String, Object>> getUsers(@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize) {
		log.info("Entered into UserController :: getUsers");
		
		return new ResponseEntity<>(userService.getUsers(pageNo, pageSize), HttpStatus.OK);
	} 

	@PostMapping(value = "users") 
	public ResponseEntity<User> addUser(@Valid @RequestBody UserDto userDto) {
		log.info("Entered into UserController :: addUser");
		User user=userService.addUser(userDto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
		
	}

	@PutMapping("users")
	public ResponseEntity<User> updateUser(@RequestBody UserDto userDto) {
		log.info("Entered into UserController :: updateUser");
		User user=userService.updateUser(userDto);
		return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
	}

	@GetMapping("users/{id}")
	public ResponseEntity<User> getUser(@PathVariable Integer id) {
		log.info("Entered into UserController :: getUser");
		User user= userService.getUser(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@DeleteMapping("users/{id}")
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
