package yte.internship.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yte.internship.app.entity.User_;
import yte.internship.app.entity.dto.UserDTO;
import yte.internship.app.entity.mapper.UserMapper;
import yte.internship.app.service.UserService;
import yte.internship.app.entity.dto.EventDTO;

import java.util.Set;

/**
 * Receives user-related requests.
 *
 * Created 13.07.2020
 * @author Umut Emre Bayramoglu
 */


@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    /**
     * Handle requests for 'getting the user profile'.
     * @return A configured response entity object with {@link UserDTO} body.
     */
    @GetMapping(value = "/profile")
    public ResponseEntity getProfile(){
        User_ user = userService.getAuthenticatedUserData();
        UserDTO userDTO = userMapper.mapToDto(user);
        userDTO.setHasWritePerm(userService.hasWritePerm(user));
        return ResponseEntity.ok(userDTO);
    }

    /**
     * Handle requests for 'getting created events by the user'.
     * @param username
     * @return A configured response entity object with set of {@link EventDTO } objects body.
     */
    @RequestMapping(value = "/users/{username}/events", method = RequestMethod.GET)
    public ResponseEntity getCreatedEventsByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getCreatedEventsOfUserByUsername(username));
    }


}
