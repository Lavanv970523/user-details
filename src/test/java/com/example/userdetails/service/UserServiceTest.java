package com.example.userdetails.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.userdetails.controllers.UserController;
import com.example.userdetails.dao.UserDao;
import com.example.userdetails.dto.UserDto;
import com.example.userdetails.entities.User;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Autowired
	ModelMapper modelMapper;

	@InjectMocks
	UserService userService;

	@Mock
	UserDao userDao = new UserDao();
	

	@BeforeEach
	void set() {
		ReflectionTestUtils.setField(userService, "userDao", userDao);
	}

	List<User> list = new ArrayList<User>();
	User u1 = new User();

	User u2 = new User();
	
	UserDto userDto = new UserDto();

	@Test
	void testGetUsers() {

		u1.setFirstName("ravi");
		u1.setLastName("last");
		u1.setAddress("laksm");
		u1.setEmail("test@test.com");
		u1.setPassword("1524");
		u1.setPhoneNumber("9876543212");

		u2.setFirstName("ravi");
		u2.setLastName("last");
		u2.setAddress("laksm");
		u2.setEmail("test@test.com");
		u2.setPassword("1524");
		u2.setPhoneNumber("9876543211");

		list.add(u1);
		list.add(u2);

		when(userDao.getUsers()).thenReturn(list);

		List<User> userList = userService.getUsers();

		assertEquals(2, userList.size());
		verify(userDao, times(1)).getUsers();
	}
	
//	@Test
//	void testAddUser() {
//		ReflectionTestUtils.setField(userService, "mapper", modelMapper);
//		ReflectionTestUtils.setField(userService, "user", user);
//
//		userDto.setFirstName("ravi");
//		userDto.setLastName("last");
//		userDto.setAddress("laksm");
//		userDto.setEmail("test@test.com");
//		userDto.setPassword("1524");
//		userDto.setPhoneNumber("9876543212");
//
//		userService.addUser(userDto);
//
//		verify(userDao, times(1)).addUser(user);
//	}
	
	@Test
	void testGetUser() {

		u1.setFirstName("ravi");
		u1.setId(1);
		u1.setLastName("last");
		u1.setAddress("laksm");
		u1.setEmail("test@test.com");
		u1.setPassword("1524");
		u1.setPhoneNumber("9876543212");

		when(userDao.getUser(1)).thenReturn(u1);
		User user = userService.getUser(1);
		assertEquals(1, user.getId());
		assertEquals("ravi", user.getFirstName());
		assertEquals("last", user.getLastName());
		assertEquals("test@test.com", user.getEmail());
		verify(userDao, times(1)).getUser(1);
	}

}
