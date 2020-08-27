package yte.internship.app.entity.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationDTO {

    private String title;
    private String eventName;
    private String participantName;
    private String participantUsername;
    private String message;
    private Integer type;
}
