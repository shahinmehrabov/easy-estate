package easyestate.dto;

import java.util.Set;

import easyestate.model.Role;

public class UserRegistrationDTO {
	
	private Long id;
	private String username;
	private String number;
	private String name;
	private String surname;
	private String email;
	private String password;
	private Set<Role> roles;
	private String profileImage;
	
	public UserRegistrationDTO() {}

	public UserRegistrationDTO(Long id, String username, String number, String name, String surname, String email, String password,
			Set<Role> roles, String profileImage) {
		this.id = id;
		this.username = username;
		this.number = number;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.profileImage = profileImage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getProfileImage() {
		return profileImage;
	}



	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

}