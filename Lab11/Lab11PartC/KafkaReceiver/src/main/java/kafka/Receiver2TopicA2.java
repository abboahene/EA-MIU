package kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver2TopicA2 {

    @KafkaListener(topics = {"TopicA2"})
    public void receive(@Payload String message) {
        System.out.println("Receiver2TopicA2 received message= "+ message);
    }

}