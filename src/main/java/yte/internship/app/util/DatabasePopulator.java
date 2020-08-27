package yte.internship.app.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yte.internship.app.security.CustomUserDetailsManager;
import yte.internship.app.repository.AuthorityRepository;

@Component
@RequiredArgsConstructor
public class DatabasePopulator {

	private final CustomUserDetailsManager customUserDetailsManager;
	private final AuthorityRepository authorityRepository;

}
