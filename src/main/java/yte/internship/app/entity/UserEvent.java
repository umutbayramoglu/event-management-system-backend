package yte.internship.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_participated_event")
public class UserEvent {

    @EmbeddedId
    private UserEventId userEventId = new UserEventId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User_ user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("eventId")
    @JoinColumn(name = "event_id",referencedColumnName = "id")
    private Event event;

    @JoinColumn(name = "enrolled_date")
    private Date enrolledDate = new Date();

}
