package yte.internship.app.entity.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

/**
 *  When an user participate an event, app sends generated qr code to user as email.
 *  @TicketDetailsDto object contains required information for this email.
 */
public class TicketDetailsDTO {

    private String base64ImageUrl;
    private String eventTitle;
    private String participantName;
    private String participantEmail;
    private String eventUrl;
}
