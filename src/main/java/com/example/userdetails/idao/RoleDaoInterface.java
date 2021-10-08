package com.example.userdetails.idao;

import java.util.List;

import com.example.userdetails.entities.Role;

public interface RoleDaoInterface {

	List<Role> getRoles();

	Role addRole(Role role);

	Role updateRole(Role role);

}
