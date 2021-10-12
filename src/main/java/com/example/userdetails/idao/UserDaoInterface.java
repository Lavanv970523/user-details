package com.example.userdetails.idao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.userdetails.entities.Role;
import com.example.userdetails.entities.User;
import com.example.userdetails.entities.UserRoles;

public interface UserDaoInterface {

	public Page<User> getUsers(Pageable paging);

	public User addUser(@RequestBody User user);

	public User updateUser(User user);

	public User getUser(Integer id);

	public void deleteUser(@PathVariable Integer id);

	public List<Role> getRolesOfUser(@PathVariable Integer id);

	public void assignRoles(UserRoles userRoles);

	public void removeRoles(UserRoles userRoles);
}
