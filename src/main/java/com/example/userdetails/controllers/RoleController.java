package com.example.userdetails.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.userdetails.dto.RoleDto;
import com.example.userdetails.entities.Role;
import com.example.userdetails.service.RoleService;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping("roles")
	public List<Role> getRoles() {
		return roleService.getRoles();
	}

	@PostMapping("roles")
	public void addRole(@RequestBody RoleDto roleDto) {
		roleService.addRole(roleDto);
	}

	@PutMapping("roles")
	public void updateRole(@RequestBody RoleDto roleDto) {
		roleService.updateRole(roleDto);
	}
}
