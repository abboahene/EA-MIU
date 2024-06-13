package bank.service.events;

import bank.domain.AuditRecord;
import bank.service.AuditRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableAsync
public class AccountChangeListener {

    @Autowired
    AuditRecordService auditRecordService;

    @Async
    @EventListener
    public void onEvent(AccountChangeEvent event) {
            System.out.println("Account changed, email sent: " + event.getMessage());
           AuditRecord auditRecord =  new AuditRecord(LocalDateTime.now(),
                   event.getAccountNumber(),
                   event.getMessage(),
                   event.getAmount());
            auditRecordService.save(auditRecord);
    }

}