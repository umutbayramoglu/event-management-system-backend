package yte.internship.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import yte.internship.app.entity.User_;
import yte.internship.app.entity.dto.NotificationDTO;
import yte.internship.app.repository.EventRepository;

/**
 * Receives notifications.
 *
 * Created 11.08.2020
 * @author Umut Emre Bayramoglu
 */


@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final EventRepository eventRepository;

    /**
     * Receives and handles notifications and send these to target user.
     * @param eventId
     * @param notification An {@link NotificationDTO} object.
     */
    @MessageMapping("/notifications/participated/{eventId}")
    @SendTo("/topic/notifications")
    public void sendParticipatedNotification(@DestinationVariable String eventId, @Payload NotificationDTO notification){
        User_ owner = eventRepository.findOwnerByEventId(Long.valueOf(eventId));
        messagingTemplate.convertAndSend("/topic/notifications/" + owner.getUsername(), notification);
    }
}
