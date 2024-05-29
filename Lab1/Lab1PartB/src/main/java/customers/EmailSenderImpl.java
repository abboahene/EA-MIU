package customers;

import org.springframework.stereotype.Component;

@Component("emailSender")
public class EmailSenderImpl implements EmailSender {

	String outgoingMailServer = "smtp.acme.com";

	private Logger logger;
//	private Logger logger = new LoggerImpl();

	EmailSenderImpl(Logger logger){
		this.logger = logger;
	}

	public String getOutgoingMailServer() {
		return outgoingMailServer;
	}

	public void sendEmail (String email, String message){
		System.out.println("EmailSender: sending '"+message+"' to "+email );
		logger.log("Email is sent: message= "+message +" , emailaddress ="+ email  );
	}

}
