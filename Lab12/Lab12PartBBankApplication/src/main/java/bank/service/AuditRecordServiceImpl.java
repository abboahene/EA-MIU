package bank.service;

import bank.adapters.AccountAdapter;
import bank.domain.Account;
import bank.domain.AuditRecord;
import bank.domain.Customer;
import bank.dto.AccountDTO;
import bank.dto.AccountDTOs;
import bank.integration.jms.JMSSender;
import bank.integration.logging.Logger;
import bank.repository.AccountRepository;
import bank.repository.AuditRecordRepository;
import bank.service.events.AccountChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuditRecordServiceImpl implements AuditRecordService {

	@Autowired
	private AuditRecordRepository auditRecordRepository;

	public void save(AuditRecord auditRecord) {
		auditRecordRepository.save(auditRecord);
	}

}
