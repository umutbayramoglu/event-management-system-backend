package yte.internship.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import yte.internship.app.entity.*;
import yte.internship.app.entity.dto.EventRegistrationQuestionUserAnswerDTO;
import yte.internship.app.entity.dto.UserDTO;
import yte.internship.app.entity.mapper.UserMapper;
import yte.internship.app.repository.EventRepository;
import yte.internship.app.repository.UserEventRepo;
import yte.internship.app.repository.UserRepository;
import yte.internship.app.util.exceptions.FullQuotaException;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created 13.07.2020
 *
 * @author Umut Emre Bayramoglu
 */


@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final UserEventRepo userEventRepo;
    private final UserMapper userMapper;

    public List<Event> getAllEvents() {
        return eventRepository.findAll(Sort.by("createdDate").descending());
    }


    public void addEvent(Event event) {
        try {
            User_ user = (User_) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            event.setOwner(user);
            event.setStatus("active");
            event.setOwnerName(String.format("%s %s", user.getName(), user.getSurName()));
            event.setQuotaStatus(true);
            event.setParticipantCount(0);
            event.setCreatedDate(new Date());
            if (event.getHasRegistrationQuestion()) {
                for (EventRegistrationQuestion question : event.getRegistrationFormQuestions()) {
                    question.setEvent(event);
                }
            }

            user.getCreatedEvents().add(event);
            eventRepository.save(event);
        } catch (ClassCastException e) {
            throw new ClassCastException("User is not valid.");
        }

    }


    public void updateEventById(Event event, String id) {

        Optional<Event> oldEvent = eventRepository.findById(Long.valueOf(id));
        oldEvent.get().setTitle(event.getTitle());
        oldEvent.get().setQuota(event.getQuota());
        oldEvent.get().setExplanation(event.getExplanation());
        oldEvent.get().setStartDate(event.getStartDate());
        oldEvent.get().setEndDate(event.getEndDate());
        oldEvent.get().setLanguage(event.getLanguage());
        oldEvent.get().setLocation(event.getLocation());
        oldEvent.get().setImg(event.getImg());

        eventRepository.save(oldEvent.get());
    }


    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    public Set<UserDTO> getEventParticipants(Long eventId) {
        Set<UserDTO> users = eventRepository.findUserEventsByEventId(eventId).stream()
                .map(userEvent -> userMapper.mapToDto(userEvent.getUser()))
                .collect(Collectors.toSet());
        return users;
    }


    public Boolean findUserParticipatedAnEvent(Long eventId, String username) {
        return eventRepository.findUserEventsByEventId(eventId).stream()
                .anyMatch(userEvent -> userEvent.getUser().getUsername().equals(username));
    }


    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }


    public boolean attendEvent(Long eventId, String username,
                               List<EventRegistrationQuestionUserAnswer> registrationFormUserAnswers) throws FullQuotaException {
        Event event = eventRepository.findById(eventId).orElseThrow(EntityNotFoundException::new);
        User_ user = userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);

        if (event.isQuotaStatus()) {
            UserEvent userEvent = new UserEvent();
            userEvent.setUser(user);
            userEvent.setEvent(event);

            for (int i = 0; i < event.getRegistrationFormQuestions().size(); i++) {
                EventRegistrationQuestion question = event.getRegistrationFormQuestions().get(i);
                EventRegistrationQuestionUserAnswer userAnswer = registrationFormUserAnswers.get(i);

                userAnswer.setQuestion(question);
                userAnswer.setUser(user);
                question.getUserAnswers().add(userAnswer);

            }

            event.setParticipantCount(event.getParticipantCount() + 1);

            if (event.getParticipantCount() == event.getQuota())
                event.setQuotaStatus(false);

            eventRepository.save(event);
            userRepository.save(user);
            userEventRepo.save(userEvent);

        } else {
            throw new FullQuotaException("Quota is full.");
        }
        return true;
    }


    public boolean cancelAttending(Long eventId, String username) {
        Event event = eventRepository.findById(eventId).orElseThrow(EntityNotFoundException::new);
        User_ user = userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);

        userEventRepo.deleteById(new UserEventId(user.getId(), event.getId()));
        event.setParticipantCount(event.getParticipantCount() - 1);
        if (event.getParticipantCount() < event.getQuota())
            event.setQuotaStatus(true);

        eventRepository.save(event);
        userRepository.save(user);
        return true;
    }


    public List<Date> getEnrolledDates(Long eventId) {
        List<Date> enrolledDates = eventRepository.findUserEventsByEventId(eventId)
                .stream()
                .map(userEvent -> userEvent.getEnrolledDate())
                .collect(Collectors.toList());
        return enrolledDates;
    }


    public boolean saveEventPoll(Long eventId, List<EventPollUserSelection> eventPollUserAnswers) {
        Event event = eventRepository.findById(eventId).orElseThrow(EntityNotFoundException::new);

        for (int i = 0; i < event.getPoll().getQuestions().size(); i++) {
            EventPollQuestion question = event.getPoll().getQuestions().get(i);
            EventPollUserSelection userAnswer = eventPollUserAnswers.get(i);

            userAnswer.setQuestion(question);
            userAnswer.setUser(eventPollUserAnswers.get(i).getUser());
            question.getUserSelections().add(userAnswer);

        }

        eventRepository.save(event);

        return true;
    }
}
