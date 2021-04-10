package test.rabbitmq.demo.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @NotBlank(message = "Sender must not be blank")
    private String sender;

    @NotBlank(message = "The message must not be blank")
    private String message;

    private NotificationPriority priority;

    public enum NotificationPriority {
        LOW, MEDIUM, HIGH;
    }
}
