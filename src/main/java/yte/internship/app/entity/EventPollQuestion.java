package yte.internship.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class EventPollQuestion {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(columnDefinition = "text", name = "question_text")
    private String questionText;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "polL_question_id",referencedColumnName = "id")
    private EventPoll poll;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "question",fetch = FetchType.EAGER)
    private Set<EventPollQuestionOption> choices;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "question",fetch = FetchType.EAGER)
    private Set<EventPollUserSelection> userSelections;


}
