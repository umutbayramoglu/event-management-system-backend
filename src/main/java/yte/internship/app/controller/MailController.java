package yte.internship.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yte.internship.app.entity.dto.TicketDetailsDTO;
import yte.internship.app.service.MailService;

import javax.mail.MessagingException;

/**
 * Receives mail requests.
 *
 * Created 11.08.2020
 * @author Umut Emre Bayramoglu
 */

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    /**
     *  Handle 'send email' requests when user participate an event.
     * @param ticketDetailsDTO  Contains required information for email.
     * @throws MessagingException
     */
    @RequestMapping(value = "/mail/send-qr-code", method = RequestMethod.POST)
    public ResponseEntity sendParticipatedEmail(@RequestBody TicketDetailsDTO ticketDetailsDTO ){
        try {
            mailService.sendQrCodeMail(ticketDetailsDTO);
            return ResponseEntity.ok(true);
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getLocalizedMessage());
        }
    }
}
