package com.example.userdetails.idao;

import java.util.List;

import com.example.userdetails.entities.Role;

public interface RoleDaoInterface {

	List<Role> getRoles();

	void addRole(Role role);

	void updateRole(Role role);

}
