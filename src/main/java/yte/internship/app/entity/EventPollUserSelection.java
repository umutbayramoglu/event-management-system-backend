package yte.internship.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class EventPollUserSelection {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private EventPollQuestion question;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private EventPollQuestionOption selectedOption;


}
