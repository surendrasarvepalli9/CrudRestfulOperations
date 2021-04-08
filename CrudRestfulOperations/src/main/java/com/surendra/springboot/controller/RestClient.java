package com.surendra.springboot.controller;

import java.awt.PageAttributes.MediaType;
import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.surendra.springboot.entity.User;

public class RestClient {

	private static final String GET_ALL_USERS_API = "http://localhost:8081/api/users";
	private static final String GET_USER_BY_ID_API = "http://localhost:8081/api/users/{id}";
	private static final String CREATE_USER_API = "http://localhost:8081/api/users";
	private static final String UPDATE_USER_API = "http://localhost:8081/api/users/{id}";
	private static final String DELETE_USER_API = "http://localhost:8081/api/users/{id}";

	static RestTemplate template = new RestTemplate();

	public static void main(String[] args) {
		callGetAllUsersApi();
		callGetUserByIDApi();
		createNewUserApi();
		updateUserApi();
		deleteUserApi();

	}

	public static void callGetAllUsersApi() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>("para,eters", headers);

		ResponseEntity<String> result = template.exchange(GET_ALL_USERS_API, HttpMethod.GET, entity, String.class);

		System.out.println(result);

	}

	public static void callGetUserByIDApi() {

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", 1);

		User user = template.getForObject(GET_USER_BY_ID_API, User.class, map);

		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getEmail());

	}

	public static void createNewUserApi() {

		User createNewUser = new User("Muthu", "Chitteti", "chitteti@gmail.com");
		ResponseEntity<User> newUser = template.postForEntity(CREATE_USER_API, createNewUser, User.class);
		System.out.println(newUser.getBody());

	}

	public static void updateUserApi() {

		HashMap<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", 1);
		User updateUser = new User("Pavani", "Sarvepalli", "chitteti@gmail.com");
		template.put(UPDATE_USER_API, updateUser, params);

	}

	public static void deleteUserApi() {

		HashMap<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", 3);
		template.delete(DELETE_USER_API, params);

	}

}
