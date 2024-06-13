package bank.service;

import bank.domain.AuditRecord;
import bank.dto.AccountDTO;
import bank.dto.AccountDTOs;

public interface AuditRecordService {

    public void save(AuditRecord auditRecord);
}
