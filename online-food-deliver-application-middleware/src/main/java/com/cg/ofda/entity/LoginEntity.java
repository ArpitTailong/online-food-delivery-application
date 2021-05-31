package com.cg.ofda.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/* This is an Entity class
 * 
 * 
 */
@Entity
/*To create table "login"*/
@Table(name = "login")
public class LoginEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * All the private members are defined here with suitable datatypes
	 * 
	 */

	@Id
	/*To create user_id column*/
	@Column(name = "user_id", length = 19)
	@GeneratedValue	
	private Long userid;

	/*To create user_name column*/
	@Column(name = "user_name", unique = true, length = 50)
	private String userName;

	/*To create password column*/
	@Column(name = "password", length = 50)
	private String password;
	
	@OneToOne
	@JoinColumn(name="cust_id")
	private CustomerEntity customer;

	/*
	 * A default Constructor with no implementation
	 */
	public LoginEntity() {
		// default
	}

	/*
	 * A Parameterized Constructor for assigning the values to private members
	 */
	

	public LoginEntity(Long userid, String userName, String password, CustomerEntity customer) {
		super();
		this.userid = userid;
		this.userName = userName;
		this.password = password;
		this.customer = customer;
	}

	

	/*
	 * Corresponding Getters and Setters for private members
	 * 
	 */

	public Long getUserid() {
		return userid;
	}



	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	/* HashCode and Equals*/
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginEntity other = (LoginEntity) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}

	


	/*
	 * toString() method overridden here
	 * 
	 */
	
	
	@Override
	public String toString() {
		return String.format("LoginEntity [userid=%s, userName=%s, password=%s, customer=%s]", userid, userName,
				password, customer);
	}
	

	

}
