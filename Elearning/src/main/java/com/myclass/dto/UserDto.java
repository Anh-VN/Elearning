package com.myclass.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDto {

	private int id;
	
	@NotBlank(message = "{user.email.notBlank}")
	@Email(message = "{user.email.invalid}")
	private String email;
	
	@NotBlank(message = "{user.password.notBlank}")
	private String password;
	
	@NotBlank(message = "{user.confirm.notBlank}")
	private String confirm;
	
	@NotBlank(message = "{user.fullname.notBlank}")
	private String fullname;
	
	private String avatar;
	
	
	private String phone;
	

	private String address;
	
	private int roleId;

 
	public UserDto()
	{
		
	}

	public UserDto(int id, String email, String password, String confirm, String fullname, String avatar, String phone,
			String address, int roleId) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
		this.fullname = fullname;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.roleId = roleId;
	}

	
	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
