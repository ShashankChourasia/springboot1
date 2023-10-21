package com.in17.springboot.blogrestapi.userinfo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor 
@Table(name="USERINFO")
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@NotNull(message="Name shouldn't be empty")
	private String name;
	@NotBlank(message = "Please enter proper user name")
    @Size(min= 10, message = "Password should be atleast 8 characters")
	private String password;
//	@NotBlank
	private String roles;
//	@Email("Invalid Email address")
//	private String email;
	
	public User() {
		super();
	}
	
	public User(int id, String name, String password, String roles) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getRoles() {
		return roles;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", roles=" + roles + "]";
	}
	
}
