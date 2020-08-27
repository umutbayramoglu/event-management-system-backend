package yte.internship.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class EventPollQuestionOption {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;


    @Column(name = "choice_text",columnDefinition = "text")
    private String choiceText;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "polL_question_choice_id",referencedColumnName = "id")
    private EventPollQuestion question;


    @OneToOne(mappedBy = "selectedOption",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private EventPollUserSelection selection;
}
