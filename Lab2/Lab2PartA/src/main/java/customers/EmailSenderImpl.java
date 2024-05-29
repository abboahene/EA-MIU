package customers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("emailSender")
public class EmailSenderImpl implements EmailSender {

	@Value("${outgoing.mail.server}")
	String outgoingMailServer;

	private Logger logger;

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
