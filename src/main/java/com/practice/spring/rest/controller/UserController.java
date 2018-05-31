/**
 * 
 */
package com.practice.spring.rest.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paractice.spring.rest.model.Response;
import com.paractice.spring.rest.model.User;

/**
 * Controller class
 * 
 * @author equester
 * @date 11-May-2018
 */
@RestController
@RequestMapping("/")
public class UserController {
	private final String sharedKey = "myKey";

	private static final String SUCCESS_STATUS = "success";
	private static final String ERROR_STATUS = "error";
	private static final int CODE_SUCCESS = 100;
	private static final int AUTH_FAILURE = 102;
	private static final int WRONG_INPUT_FORMAT = 103;

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public Response addUser(@RequestParam(value = "key") String key, @RequestBody User userRequest) {
		System.out.println(userRequest);
		Response response = new Response();
		System.out.println("Request received -" + userRequest.toString());

		if (key.equalsIgnoreCase(sharedKey)) {
			String name = userRequest.getName();
			int age = userRequest.getAge();
			String sex = userRequest.getSex();
			if ((name != null) && (age != 0) && (sex != null)) {
				User newUser = new User(name.toUpperCase(), age, sex.toLowerCase());
				response.setCode(CODE_SUCCESS);
				response.setStatus(SUCCESS_STATUS);
				response.setUser(newUser);
			}else {
				User newUser = new User(name, age, sex);
				response.setCode(WRONG_INPUT_FORMAT);
				response.setStatus(ERROR_STATUS);
				response.setUser(newUser);
			}
		} else {
			System.out.println("Authenticaion key did not match");
			response.setCode(AUTH_FAILURE);
			response.setStatus(ERROR_STATUS);
			response.setUser(new User());
		}
		return response;
	}
	
	@RequestMapping(value="/getUser", method=RequestMethod.GET)
	public Response getUser(@RequestParam(value = "key") String key, @RequestParam(value = "name") String name) {
		Response response = new Response();
		
		if(key.equalsIgnoreCase(sharedKey)) {
			System.out.println("Got request with key - "+key+"\nName - "+name);
			response.setCode(CODE_SUCCESS);
			response.setStatus(SUCCESS_STATUS);
			response.setUser(new User(name,0, null));
		}else {
			System.out.println("Authenticaion key did not match");
			response.setCode(AUTH_FAILURE);
			response.setStatus(ERROR_STATUS);
			response.setUser(new User());
		}
		return response;
	}
}
