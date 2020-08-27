package yte.internship.app.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import javax.persistence.*;
import java.util.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "id",
        scope     = Long.class)
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column(columnDefinition="text")
    private String explanation;
    private String ownerName;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_user_id",referencedColumnName = "id")
    private User_ owner;

    @JsonIgnore
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private Set<EventQuestion> questions = new HashSet<>();

    @JsonIgnore
    @OneToOne(mappedBy = "event",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private EventPoll poll;

    @JsonIgnore
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private Set<UserEvent> userEvents = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private List<EventRegistrationQuestion> registrationFormQuestions;

    private Integer quota;
    private Integer participantCount;
    private boolean quotaStatus;
    private Date startDate;
    private Date endDate;
    private String location;
    private String language;
    private String img;
    private String status;
    private Date createdDate;
    private Boolean hasRegistrationQuestion;



}
