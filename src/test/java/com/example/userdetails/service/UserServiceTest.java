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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.userdetails.dao.UserDao;
import com.example.userdetails.dto.UserDto;
import com.example.userdetails.entities.Role;
import com.example.userdetails.entities.User;
import com.example.userdetails.exceptions.DelegationException;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

	@Autowired
	ModelMapper modelMapper;

	@InjectMocks
	UserService userService;
	
	
	@Mock
	UserDao userDao;

	@Test
	void testGetUsers() {
		Page<User> users = Mockito.mock(Page.class);
		when(userDao.getUsers(any())).thenReturn(users);

		assertEquals(4, userService.getUsers(1,1).size());
		verify(userDao, times(1)).getUsers(any());
	}

	@Test
	void testGetUser() {

		when(userDao.getUser(1)).thenReturn(
				(new User(1, "ravi", "1234", "ravi", "last", null, null, "test@test.com", null, null, null, null)));
		User user = userService.getUser(1);
		assertEquals(1, user.getId());
		assertEquals("ravi", user.getFirstName());
		assertEquals("last", user.getLastName());
		assertEquals("test@test.com", user.getEmail());
		verify(userDao, times(1)).getUser(1);
	}

	@Test
	void testAddUser() {

		ReflectionTestUtils.setField(userService, "mapper", modelMapper);

		UserDto userDto = new UserDto();
		userDto.setId(1);
		userDto.setFirstName("ravi");
		userDto.setLastName("last");
		userDto.setAddress("laksm");
		userDto.setEmail("test@test.com");
		userDto.setPassword("1524");
		userDto.setPhoneNumber("9876543212");

		when(userDao.addUser(any())).then(i -> i.getArgument(0));
		User user = userService.addUser(userDto);
		assertEquals(1, user.getId());
		assertEquals("test@test.com", user.getEmail());
		verify(userDao, times(1)).addUser(user);

	}

	@Test
	void testUpdateUser() {

		ReflectionTestUtils.setField(userService, "mapper", modelMapper);

		UserDto userDto = new UserDto();
		userDto.setId(1);
		userDto.setFirstName("ravi");
		userDto.setLastName("last");
		userDto.setAddress("laksm");
		userDto.setEmail("test@test.com");
		userDto.setPassword("1524");
		userDto.setPhoneNumber("9876543212");

		when(userDao.updateUser(any())).then(i -> i.getArgument(0));
		User user = userService.updateUser(userDto);
		assertEquals(1, user.getId());
		assertNotNull(user);
		assertEquals("test@test.com", user.getEmail());
		verify(userDao, times(1)).updateUser(user);

	}

	@Test
	void testDeleteUser() {

		userService.deleteUser(1);
		verify(userDao).deleteUser(any());

	}

	@Test
	void testGetUserRoles() {

		when(userDao.getRolesOfUser(1)).thenReturn(
				Stream.of(new Role(1, "admin", "desc", null, null, null), new Role(2, "dev", "desc", null, null, null))
						.collect(Collectors.toList()));
		List<Role> roles = userService.getRolesOfUser(1);
		assertEquals(1, roles.get(0).getRoleId());
		assertEquals(2, roles.get(1).getRoleId());
		verify(userDao, times(1)).getRolesOfUser(1);
	}

	@Test
	void testAssignUserRoles() {

		userService.assignRoles(1, 1);
		verify(userDao).assignRoles(any());

	}

	@Test
	void testRemoveUserRoles() {

		userService.removeRoles(1, 1);
		verify(userDao).removeRoles(any());

	}

	@Test
	void testAddUserNegative() {

		UserDto userDto = new UserDto();
		userDto.setId(1);
		userDto.setFirstName("ravi");
		userDto.setLastName("last");
		userDto.setAddress("laksm");
		userDto.setEmail("test@test.com");
		userDto.setPassword("1524");
		userDto.setPhoneNumber("9876543212");

		assertThatExceptionOfType(DelegationException.class).isThrownBy(() -> userService.addUser(userDto));

	}
	
	@Test
	void testGetUsersNegative() {

		UserDao dao = null;
		ReflectionTestUtils.setField(userService, "userDao", dao);
		assertThatExceptionOfType(DelegationException.class).isThrownBy(() -> userService.getUsers(1,1));

	}
	
	@Test
	void testUpdateUserNegative() {
		assertThatExceptionOfType(DelegationException.class).isThrownBy(() -> userService.updateUser(null));

	}
	
	@Test
	void testGetUserNegative() {
		UserDao dao = null;
		ReflectionTestUtils.setField(userService, "userDao", dao);
		assertThatExceptionOfType(DelegationException.class).isThrownBy(() -> userService.getUser(null));

	}
	
	@Test
	void testDeleteUserNegative() {
		UserDao dao = null;
		ReflectionTestUtils.setField(userService, "userDao", dao);
		assertThatExceptionOfType(DelegationException.class).isThrownBy(() -> userService.deleteUser(1));

	}
	
	@Test
	void testGetRolesOfUserNegative() {
		UserDao dao = null;
		ReflectionTestUtils.setField(userService, "userDao", dao);
		assertThatExceptionOfType(DelegationException.class).isThrownBy(() -> userService.getRolesOfUser(1));

	}
	
	@Test
	void testAssignRolesNegative() {
		UserDao dao = null;
		ReflectionTestUtils.setField(userService, "userDao", dao);
		assertThatExceptionOfType(DelegationException.class).isThrownBy(() -> userService.assignRoles(1,1));

	}
	
	@Test
	void testRemoveRolesNegative() {
		UserDao dao = null;
		ReflectionTestUtils.setField(userService, "userDao", dao);
		assertThatExceptionOfType(DelegationException.class).isThrownBy(() -> userService.removeRoles(1,1));

	}
	
}
