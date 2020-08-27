package yte.internship.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import yte.internship.app.entity.Event;
import yte.internship.app.entity.User_;
import yte.internship.app.repository.UserRepository;

import java.util.Set;

/**
 *
 * Created 13.07.2020
 * @author Umut Emre Bayramoglu
 */

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User_ getAuthenticatedUserData() {
        User_ user = (User_) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }

    public Boolean hasWritePerm(User_ user) {
        GrantedAuthority authentication = user
                .getAuthorities()
                .stream()
                .filter(auth -> "WRITE".equals(auth.getAuthority()))
                .findAny()
                .orElse(null);

        return authentication != null ? true : false;
    }

    public Set<Event> getCreatedEventsOfUserByUsername(String username) {
        Set<Event> events = userRepository.findCreatedEventsByUsername(username);
        return events;
    }
}
