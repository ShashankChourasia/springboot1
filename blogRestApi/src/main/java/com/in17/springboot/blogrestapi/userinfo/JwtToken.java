package com.in17.springboot.blogrestapi.userinfo;

public class JwtToken {
	private int userId;
	private String jwtToken;
	
	public JwtToken() {
		super();
	}

	public JwtToken(int userId, String jwtToken) {
		super();
		this.userId = userId;
		this.jwtToken = jwtToken;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
