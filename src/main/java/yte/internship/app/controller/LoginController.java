package yte.internship.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yte.internship.app.security.cases.login.LoginRequest;
import yte.internship.app.security.cases.login.LoginResponse;
import yte.internship.app.security.cases.login.LoginService;
import yte.internship.app.entity.dto.UserDTO;
import javax.validation.Valid;

/**
 *
 *
 * Created 11.07.2020
 * @author Umut Emre Bayramoglu
 */


@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	/**
	 * Receives login requests and authenticate user.
	 * @param loginRequest	{@link LoginRequest} object.
	 * @return  {@link LoginResponse} with @{@link UserDTO } body, if authentication is successful.
	 * @return  {@link LoginResponse} with 'false' body, otherwise.
	 */
	@PostMapping(value = "/login")
	public LoginResponse login(@Valid @RequestBody final LoginRequest loginRequest) {
		return loginService.authenticate(loginRequest);
	}
}
