package yte.internship.app.security.cases.login;

import lombok.Getter;

@Getter
public class LoginResponse {

	private String token;
	private boolean success;
	private Object user;

	public LoginResponse(final String token) {
		this.token = token;
	}

	public LoginResponse(String token, boolean success, Object user) {
		this.token = token;
		this.success = success;
		this.user = user;
	}

	public LoginResponse(boolean success) {
		this.success = success;
	}
}
