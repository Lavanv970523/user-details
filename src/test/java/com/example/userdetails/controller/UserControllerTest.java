package com.example.userdetails.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.userdetails.UserDetailsApplication;
import com.example.userdetails.dto.UserDto;
import com.example.userdetails.entities.Role;
import com.example.userdetails.entities.User;
import com.example.userdetails.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = UserDetailsApplication.class)
@WebAppConfiguration
class UserControllerTest {

	protected MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@MockBean
	UserService userService;

	@BeforeEach
	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	void testGetUsers() throws Exception {

		UserDto userDto = new UserDto();
		userDto.setFirstName("ravi");
		userDto.setUserName("kajs");
		userDto.setLastName("last");
		userDto.setAddress("laksm");
		userDto.setEmail("test@test.com");
		userDto.setPassword("1524");
		userDto.setPhoneNumber("9876543212");
		Map<String, Object> m = new HashMap<>();
		m.put("users", userDto);
		when(userService.getUsers(1,1)).thenReturn(m);

		String uri = "/users";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		assertNotNull(content);
	}

	@Test
	void testAddUser() throws Exception {
		UserDto userDto = new UserDto();
		userDto.setFirstName("ravi");
		userDto.setUserName("kajs");
		userDto.setLastName("last");
		userDto.setAddress("laksm");
		userDto.setEmail("test@test.com");
		userDto.setPassword("1524");
		userDto.setPhoneNumber("9876543212");
		User user = new User(1, "ravi", "1234", "ravi", "last", null, null, "test@test.com", null, null, null, null);
		Mockito.when(userService.addUser(Mockito.any(UserDto.class))).thenReturn(user);
		String uri = "/users";
		String inputJson = mapToJson(userDto);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		User users = mapFromJson(content, User.class);
		assertNotNull(users);
	}

	@Test
	void testUpdateUser() throws Exception {
		UserDto userDto = new UserDto();
		userDto.setFirstName("ravi");
		userDto.setUserName("kajs");
		userDto.setLastName("last");
		userDto.setAddress("laksm");
		userDto.setEmail("test@test.com");
		userDto.setPassword("1524");
		userDto.setPhoneNumber("9876543212");
		User user = new User(1, "ravi", "1234", "ravi", "last", null, null, "test@test.com", null, null, null, null);
		Mockito.when(userService.updateUser(Mockito.any(UserDto.class))).thenReturn(user);
		String uri = "/users";
		String inputJson = mapToJson(userDto);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(204, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		User users = mapFromJson(content, User.class);
		assertNotNull(users);
	}

	@Test
	void testGetUser() throws Exception {

		when(userService.getUser(1))
				.thenReturn((new User(null, "Ravi", "Ravi", "1234", "Ravi", null, null, null, null, null, null, null)));

		String uri = "/users/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		User user = mapFromJson(content, User.class);
		assertNotNull(user);
	}

	@Test
	void testGetRolesOfUser() throws Exception {

		when(userService.getRolesOfUser(1)).thenReturn(
				Stream.of(new Role(1, "admin", "desc", null, null, null), new Role(2, "dev", "desc", null, null, null))
						.collect(Collectors.toList()));

		String uri = "/users/1/roles";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		Role[] roles = mapFromJson(content, Role[].class);
		assertNotNull(roles);
	}

	@Test
	void testDelete() throws Exception {

		String uri = "/users/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

}
