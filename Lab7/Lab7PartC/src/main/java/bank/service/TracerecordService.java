package bank.service;

import bank.domain.Tracerecord;
import bank.repository.TracerecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TracerecordService {

	@Autowired
	private TracerecordRepository tracerecordRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Tracerecord save(Tracerecord tracerecord){
		return tracerecordRepository.save(tracerecord);
	}

}
