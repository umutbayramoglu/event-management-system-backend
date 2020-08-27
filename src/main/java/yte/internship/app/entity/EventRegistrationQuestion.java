package yte.internship.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event_registration_questions")
public class EventRegistrationQuestion {

    @Id
    @GeneratedValue
    private Long id;

    private String questionText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id",referencedColumnName = "id")
    private Event event;

    @JsonIgnore
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private List<EventRegistrationQuestionUserAnswer> userAnswers;
}
