package test.rabbitmq.demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import test.rabbitmq.demo.notification.Notification;

@Component
public class RabbitMQConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void getNotification(Notification notification) {
        LOG.info("You have received a notification from {} with {} priority.\nMessage: {}.", notification.getSender(), notification.getPriority(), notification.getMessage());
    }
}
