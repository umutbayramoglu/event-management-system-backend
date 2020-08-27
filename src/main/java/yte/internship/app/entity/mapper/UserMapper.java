package yte.internship.app.entity.mapper;

import org.mapstruct.Mapper;
import yte.internship.app.entity.User_;
import yte.internship.app.entity.dto.UserDTO;

import java.util.List;


@Mapper(componentModel =  "spring")
public interface UserMapper {

    UserDTO mapToDto(User_ user);
    List<UserDTO> mapToDTO(List<User_> users);

    User_ mapToEntity(UserDTO userDTO);
    List<User_> mapToEntity(List<UserDTO> users);

}
