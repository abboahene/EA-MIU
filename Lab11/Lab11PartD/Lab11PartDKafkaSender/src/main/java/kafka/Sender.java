package kafka;

import kafka.dto.CreateAccountDTO;
import kafka.dto.DepositWithdrawDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void createAccount(CreateAccountDTO message){
        kafkaTemplate.send("createAccount", message);
    }
    public void deposit(DepositWithdrawDTO message){
        kafkaTemplate.send("deposit", message);
    }
    public void withdraw(DepositWithdrawDTO message){
        kafkaTemplate.send("withdraw", message);
    }

}
