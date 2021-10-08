package com.example.userdetails.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.userdetails.dao.RoleDao;
import com.example.userdetails.dao.UserDao;
import com.example.userdetails.dto.RoleDto;
import com.example.userdetails.dto.UserDto;
import com.example.userdetails.entities.Role;
import com.example.userdetails.entities.User;
import com.example.userdetails.exceptions.DaoException;
import com.example.userdetails.exceptions.DelegationException;

@SpringBootTest
class RoleServiceTest {

	@Mock
	RoleDao roleDao;
	
	@Autowired
	ModelMapper modelMapper;

	@InjectMocks
	RoleService roleService;
	
	@Test
	void testGetRoles() {

		when(roleDao.getRoles()).thenReturn(
				Stream.of(new Role(1, "admin", "desc", null, null, null), new Role(2, "dev", "desc", null, null, null))
						.collect(Collectors.toList()));
		List<Role> roles = roleService.getRoles();
		assertEquals(1, roles.get(0).getRoleId());
		assertEquals(2, roles.get(1).getRoleId());
		verify(roleDao, times(1)).getRoles();
	}
	
	@Test
	void testAddRole() {

		ReflectionTestUtils.setField(roleService, "mapper", modelMapper);

		RoleDto roleDto = new RoleDto();
		roleDto.setRoleId(1);
		roleDto.setName("admin");
		roleDto.setDesc("desc");

		when(roleDao.addRole(any())).then(i -> i.getArgument(0));
		Role role = roleService.addRole(roleDto);
		assertEquals(1, role.getRoleId());
		assertNotNull(role);
		assertEquals("admin", role.getName());
		verify(roleDao, times(1)).addRole(role);

	}
	
	@Test
	void testUpdateRole() {

		ReflectionTestUtils.setField(roleService, "mapper", modelMapper);

		RoleDto roleDto = new RoleDto();
		roleDto.setRoleId(1);
		roleDto.setName("admin");
		roleDto.setDesc("desc");

		when(roleDao.updateRole(any())).then(i -> i.getArgument(0));
		Role role = roleService.updateRole(roleDto);
		assertEquals(1, role.getRoleId());
		assertNotNull(role);
		assertNotNull(role.getModifiedOn());
		assertEquals("admin", role.getName());
		verify(roleDao, times(1)).updateRole(role);

	}
	
	
	@Test
	void testGetRolesNegative() {

		RoleDao dao = null;
		ReflectionTestUtils.setField(roleService, "roleDao", dao);
		assertThatExceptionOfType(DaoException.class).isThrownBy(() -> roleService.getRoles());

	}
	
	@Test
	void testAddRoleNegative() {

		RoleDto roleDto = new RoleDto();
		roleDto.setRoleId(1);
		RoleDao dao = null;
		ReflectionTestUtils.setField(roleService, "roleDao", dao);
		assertThatExceptionOfType(DaoException.class).isThrownBy(() -> roleService.addRole(roleDto));

	}
	 
	@Test
	void testUpdateRoleNegative() {

		RoleDto roleDto = new RoleDto();
		roleDto.setRoleId(1);
		RoleDao dao = null;
		ReflectionTestUtils.setField(roleService, "roleDao", dao);
		assertThatExceptionOfType(DaoException.class).isThrownBy(() -> roleService.updateRole(roleDto));

	}

	
}
