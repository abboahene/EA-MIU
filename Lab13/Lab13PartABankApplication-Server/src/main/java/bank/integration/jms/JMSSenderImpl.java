package bank.integration.jms;


import bank.integration.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JMSSenderImpl implements JMSSender{
	@Autowired
	private Logger logger;
	public void sendJMSMessage (String text){
		logger.log("JMSSender: sending JMS message ="+text);
	}

}
