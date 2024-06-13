package jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
 
    @JmsListener(destination = "taxQueue")
    public void receiveMessage(final String msg) {
        try {
            System.out.println("JMS receiver received message: " + msg);
        } catch (Exception e) {
            System.out.println("JMS receiver: Error occurred");
        }
     }

}

