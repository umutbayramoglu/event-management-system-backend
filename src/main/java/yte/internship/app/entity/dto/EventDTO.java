package yte.internship.app.entity.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import yte.internship.app.entity.EventRegistrationQuestion;
import yte.internship.app.entity.User_;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "id",
        scope     = Long.class)
public class EventDTO {
    private Long id;
    private String title;
    private User_ owner;
    private String ownerName;
    private String explanation;
    private Integer quota;
    private Date startDate;
    private Date endDate;
    private String location;
    private String language;
    private String img;
    private String status;
    private Integer participantCount;
    private Boolean hasRegistrationQuestion;
    private List<EventRegistrationQuestion> registrationFormQuestions;

}
