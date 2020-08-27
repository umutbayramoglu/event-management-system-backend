package yte.internship.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yte.internship.app.entity.dto.EventDTO;
import yte.internship.app.entity.Event;
import yte.internship.app.entity.dto.EventRegistrationQuestionUserAnswerDTO;
import yte.internship.app.entity.mapper.EventMapper;
import yte.internship.app.entity.mapper.EventRegistrationQuestionUserAnswerMapper;
import yte.internship.app.service.EventService;
import yte.internship.app.util.exceptions.FullQuotaException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Receives event-related requests.
 * <p>
 * Created 13.07.2020
 *
 * @author Umut Emre Bayramoglu
 */

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;
    private final EventRegistrationQuestionUserAnswerMapper registrationQuestionUserAnswerMapper;

    /**
     * Handle requests for 'getting the all events'.
     *
     * @return A configured response entity object that contains http status, header and body with requested data.
     */
    @GetMapping("/events")
    public ResponseEntity showEvent() {
        return ResponseEntity.ok(eventMapper.mapToDto(eventService.getAllEvents()));
    }


    /**
     * Receive and handle requests for 'adding event' operation.
     *
     * @param eventDTO Contains event data's
     * @return A configured response entity object that contains http status, header and body with requested data.
     */
    @RequestMapping(value = "/events", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public ResponseEntity addNewEvent(@RequestBody EventDTO eventDTO) {
        eventDTO.setHasRegistrationQuestion(eventDTO.getRegistrationFormQuestions().isEmpty() ? false : true);
        Event event = eventMapper.mapToEntity(eventDTO);
        try {
            eventService.addEvent(event);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }


    /**
     * Handle requests for 'getting the event'.
     *
     * @param id Id of requested event.
     * @return A configured response entity object that contains http status, header and body with requested data.
     */
    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    public ResponseEntity getEventById(@PathVariable String id) {
        try {
            Event event = eventService.getEventById(Long.valueOf(id));
            return ResponseEntity.ok(eventMapper.mapToDto(event));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }


    /**
     * Handle requests for 'getting participants of the event'
     *
     * @param id Id of event
     * @return A configured response entity object that contains http status, header and body with requested data.
     */
    @RequestMapping(value = "/events/{id}/participants", method = RequestMethod.GET)
    public ResponseEntity getEventParticipants(@PathVariable String id) {
        try {
            return ResponseEntity.ok(eventService.getEventParticipants(Long.valueOf(id)));
        } catch (ClassCastException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }


    /**
     * Handle requests for 'checking the user participate an event with given id or not'
     *
     * @param eventId
     * @param username
     * @return true if user participated an event with given id, false otherwise.
     */
    @RequestMapping(value = "/events/{eventId}/participants/{username}", method = RequestMethod.GET)
    public ResponseEntity checkUserParticipatedAnEvent(@PathVariable String eventId, @PathVariable String username) {
        try {
            Boolean res = eventService.findUserParticipatedAnEvent(Long.valueOf(eventId), username);
            return ResponseEntity.ok(res);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        } catch (ClassCastException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }


    /**
     * Handle requests for 'getting the data's about when users enrolled to the event'.
     *
     * @param eventId
     * @return A configured response entity object that contains http status, header and body with requested data.
     */
    @RequestMapping(value = "/events/{eventId}/participants-by-date", method = RequestMethod.GET)
    public ResponseEntity getEventEnrolledDatesById(@PathVariable String eventId) {
        try {
            return ResponseEntity.ok(eventService.getEnrolledDates(Long.valueOf(eventId)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        } catch (ClassCastException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found");
        }
    }


    /**
     * Handle requests for 'deleting the event'.
     *
     * @param id Id of event
     * @return A response entity with OK status.
     */
    @RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEventById(@PathVariable String id) {
        eventService.deleteEventById(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Handle requests for 'updating the event'.
     *
     * @param id       Id of event
     * @param eventDTO Contains new event data's.
     * @return A response entity with OK status.
     */
    @RequestMapping(value = "/events/{id}", method = RequestMethod.PATCH, consumes = MediaType.ALL_VALUE)
    public ResponseEntity updateEventById(@PathVariable String id, @RequestBody EventDTO eventDTO) {
        Event event = eventMapper.mapToEntity(eventDTO);
        eventService.updateEventById(event, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Handle requests for 'attending the event'.
     *
     * @param eventId  Id of event
     * @param username
     * @return A configured response entity object that contains http status, header and body with requested data.
     */
    @RequestMapping(value = "/events/{eventId}/participants/{username}", method = RequestMethod.POST)
    public ResponseEntity attendEvent(@PathVariable String eventId, @PathVariable String username,
                                      @RequestBody List<EventRegistrationQuestionUserAnswerDTO> registrationFormUserAnswers) {
        try {
            eventService.attendEvent(Long.valueOf(eventId), username, registrationQuestionUserAnswerMapper.mapToEntity(registrationFormUserAnswers));
            return ResponseEntity.ok(true);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        } catch (ClassCastException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        } catch (FullQuotaException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }


    /**
     * Handle requests for 'cancelling the event attending'.
     *
     * @param eventId
     * @param username
     * @return A configured response entity object that contains http status, header and body with requested data.
     */
    @RequestMapping(value = "/events/{eventId}/participants/{username}", method = RequestMethod.DELETE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity cancelAttending(@PathVariable String eventId, @PathVariable String username) {
        try {
            return ResponseEntity.ok(eventService.cancelAttending(Long.valueOf(eventId), username));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        } catch (ClassCastException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
    }


}
