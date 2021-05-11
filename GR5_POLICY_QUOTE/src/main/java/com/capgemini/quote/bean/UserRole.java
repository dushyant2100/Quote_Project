package com.capgemini.quote.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//User Role Entity Class
@Entity
@Table(name = "UserRole")
public class UserRole {
	@Id
	@Column(name="userName")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="roleCode")
	private String roleCode;
 
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRole(String userName, String password, String roleCode) {
		super();
		this.userName = userName;
		this.password = password;
		this.roleCode = roleCode;
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
	
	public String getRoleCode() {
		return roleCode;
	}
	
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	@Override
	public String toString() {
		return "\nUser [userName=" + userName + ", password=" + password + ", roleCode=" + roleCode + "]";
	}

}
