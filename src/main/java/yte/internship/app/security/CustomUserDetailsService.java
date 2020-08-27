package yte.internship.app.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import yte.internship.app.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public CustomUserDetailsService(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		return userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException(username)
		);
	}
}
