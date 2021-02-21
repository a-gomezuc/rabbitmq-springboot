package test.rabbitmq.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.rabbitmq.demo.notification.Notification;
import test.rabbitmq.demo.service.RabbitMQService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "rabbitmq", consumes = MediaType.APPLICATION_JSON_VALUE)
public class RabbitMQController {

    private RabbitMQService rabbitMQService;

    @Autowired
    public RabbitMQController(RabbitMQService rabbitMQService) {
        this.rabbitMQService = rabbitMQService;
    }

    @RequestMapping(path = "{routingKey}", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> sendNotification(@PathVariable String routingKey, @Valid @RequestBody Notification notification) {
        rabbitMQService.sendNotification(routingKey, notification);
        return ResponseEntity.ok("Notification sent succesfully");
    }
}
