package com.cg.ofda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.CustomerEntity;
import com.cg.ofda.entity.LoginEntity;
import com.cg.ofda.exception.CustomerException;
import com.cg.ofda.exception.LoginException;
import com.cg.ofda.model.LoginModel;
import com.cg.ofda.repository.ILoginRepository;
import com.cg.ofda.util.EMParserLogin;

@Service
public class LoginServiceImpl implements ILoginService {
	
	public static final String NOT_FOUND = "no user with id #";
	public static final String PRESENT = " present";
	
	/*
	 * Login Repository is Autowired 
     */

	@Autowired
	private ILoginRepository loginRepository;
	
	/*
	 * EMParserLogin is Autowired 
     */

	@Autowired
	private EMParserLogin parser;
	
	/*
	 * Default Constructor
     */

	public LoginServiceImpl() {
		this.parser = new EMParserLogin();
	}
	
	
	/*Parameterized constructor for assigning
	 * */

	public LoginServiceImpl(ILoginRepository loginRepository) {
		super();
		this.loginRepository = loginRepository;
		this.parser = new EMParserLogin();
	}

	

	/*
	 * Implementation of signIn method to signIn a user
	 */
	
	@Override
	public LoginModel signIn(String userName , String password) throws LoginException {
		return parser.parse(loginRepository.findByUserNameAndPassword(userName,password));
	}
	
	

	/*
	 * Implementation of signOut method to signOut a user
	 */

	@Override
	public boolean signOut(LoginModel login) throws LoginException {
		// implementation is done during front end
		return false;
	}


	@Override
	public LoginModel addUser(LoginModel login) throws LoginException {
		
		if (login.getUserid() == null) {
			throw new LoginException(NOT_FOUND + login.getUserid() + PRESENT);
		} else {
	
			login = parser.parse(loginRepository.save(parser.parse(login)));
		}
		return login;
	}

}