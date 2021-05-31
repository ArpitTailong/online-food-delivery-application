package com.cg.ofda.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ofda.exception.LoginException;
import com.cg.ofda.model.CustomerModel;
import com.cg.ofda.model.LoginModel;
import com.cg.ofda.service.ILoginService;

@RestController
@RequestMapping(path="/login")
@CrossOrigin
public class LoginRestController {
	
	/*
	 * Login Service is Autowired 
     */
	
	@Autowired
	private ILoginService loginService;

	/*
	 * to login
	 * params : userId
	 */
	
	@PostMapping("/signin")
	public ResponseEntity<LoginModel> user(@RequestBody LoginModel login) throws LoginException {
		String tempUserName = login.getUserName();
		String tempPassword = login.getPassword();
		if(tempUserName != null && tempPassword !=null ) {
			login=loginService.signIn(tempUserName, tempPassword);
		}if(login == null) {
			throw new LoginException("Bad Credentials");
		}
		return new ResponseEntity<>(login,HttpStatus.OK);

	}
	
	/*
	 * to register new user
	 * params : login
	 */
	
	@PostMapping
	public ResponseEntity<LoginModel> addUser(@RequestBody LoginModel login) throws LoginException {
		login = loginService.addUser(login);
		return new ResponseEntity<>(login, HttpStatus.CREATED);
		
	}
}
