package yte.internship.app.entity.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String username;
    private String name;
    private String surName;
    private Date enrolledDate;
    private String profilePic;
    private String email;
    private boolean hasWritePerm;

}
