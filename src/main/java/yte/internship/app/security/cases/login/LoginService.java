package yte.internship.app.security.cases.login;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import yte.internship.app.entity.User_;
import yte.internship.app.entity.dto.UserDTO;
import yte.internship.app.entity.mapper.UserMapper;
import yte.internship.app.security.JwtUtil;
import yte.internship.app.service.UserService;


@Service
@RequiredArgsConstructor
public class LoginService {

	private final UserMapper userMapper;
	private final UserService userService;

	@Value(value = "${security.secretKey}")
	private String secretKey;

	private final DaoAuthenticationProvider authenticationProvider;

	public LoginResponse authenticate(final LoginRequest loginRequest) {

		Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

		try {
			Authentication user = authenticationProvider.authenticate(usernamePasswordAuthenticationToken);
			String token = JwtUtil.generateToken(user, secretKey, 15);
			UserDTO userDTO = userMapper.mapToDto((User_) user.getPrincipal());
			userDTO.setHasWritePerm(userService.hasWritePerm((User_) user.getPrincipal()));
			return new LoginResponse(token,true,userDTO);
		} catch (AuthenticationException e) {

			// If credentials is not correct.
			return new LoginResponse(false);

		}
	}
}
