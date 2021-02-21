package test.rabbitmq.demo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.rabbitmq.demo.notification.Notification;

@Service
public class RabbitMQService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(String routingKey, Notification notification) {
        rabbitTemplate.convertAndSend(routingKey, notification);
    }
}
