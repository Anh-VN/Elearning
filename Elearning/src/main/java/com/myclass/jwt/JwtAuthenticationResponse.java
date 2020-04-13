package com.myclass.jwt;

public class JwtAuthenticationResponse {

	private final String jwt;

	public JwtAuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}


	public String getJwt() {
		return jwt;
	}
	
	
}
