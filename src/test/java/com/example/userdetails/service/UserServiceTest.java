package com.example.userdetails.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.userdetails.controllers.UserController;
import com.example.userdetails.dao.UserDao;
import com.example.userdetails.entities.User;
import com.example.userdetails.repositories.UserRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	UserController userController;
	
	@InjectMocks
	UserService userService;

	@Mock
	UserDao userDao = new UserDao();
	
//	@Mock
//	UserRepository userRepository;
	
	
	@BeforeEach
	void set() {
		ReflectionTestUtils.setField(userService, "userDao", userDao);
		ReflectionTestUtils.setField(userController, "userService", userService);
	}

	@Test
	void testGetUsers() {
		/*
		 * List<UserDto> list = new ArrayList<UsaerDto>(); UserDto u1 = new UserDto();
		 * u1.setFirstName("ravi");u1.setLastName("last");u1.setAddress("laksm");u1.
		 * setEmail("test@test.com");
		 * u1.setPassword("1524");u1.setPhoneNumber("9876543212");
		 * 
		 * UserDto u2 = new UserDto();
		 * u2.setFirstName("ravi");u2.setLastName("last");u2.setAddress("laksm");u2.
		 * setEmail("test@test.com");
		 * u2.setPassword("1524");u2.setPhoneNumber("9876543211");
		 */

		List<User> list = new ArrayList<User>();
		User u1 = new User();
		u1.setFirstName("ravi");
		u1.setLastName("last");
		u1.setAddress("laksm");
		u1.setEmail("test@test.com");
		u1.setPassword("1524");
		u1.setPhoneNumber("9876543212");

		User u2 = new User();
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
//		verify(dao, times(1)).getEmployeeList();
	}

}
