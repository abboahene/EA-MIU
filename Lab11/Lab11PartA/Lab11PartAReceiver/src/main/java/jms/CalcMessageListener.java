package jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CalcMessageListener {
    int result = 0;
    @JmsListener(destination = "calcQueue")
    public void receiveMessage(final String msgAsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Message msg = objectMapper.readValue(msgAsString, Message.class);
            switch (msg.getOperator()) {
                case "*":
                    result *= msg.getOperand();
                    break;
                case "-":
                    result -= msg.getOperand();
                    break;
                case "+":
                    result += msg.getOperand();
                    break;
                default:
                    System.out.println("JMS Calc Error: Operand not supported");
            }
            System.out.printf("JMS receiver received message: %s%d\n", msg.getOperator(), msg.getOperand());
            System.out.println("JMS calculator result: " + result);

        } catch (IOException e) {
            System.out.println("JMS receiver: Cannot convert : " + msgAsString+" to a Message object");
        }
     }

}

