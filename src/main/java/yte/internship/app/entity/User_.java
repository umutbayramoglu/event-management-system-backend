package yte.internship.app.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

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
public class User_ implements UserDetails, CredentialsContainer {

	@Id
	@GeneratedValue
	private Long id;

	private String username;
	@JsonIgnore
	private String password;
	private String name;
	private String surName;
	private String email;
	private Date enrolledDate;
	private String profilePic;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_AUTHORITIES",
			joinColumns = @JoinColumn(name = "USER_ID"),
			inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID"))
	private Set<Authority> authorities;

	@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy = "owner",fetch = FetchType.EAGER)
	private Set<Event> createdEvents;

	@JsonIgnore
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private Set<UserEvent> userEvents = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private Set<EventQuestion> questions = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<EventRegistrationQuestionUserAnswer> userAnswers = new ArrayList<>();

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public void eraseCredentials() {
		password = null;
	}

}
