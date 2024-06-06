package bank.aop;

import bank.integration.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Configuration
public class JMSLogAdvice {
        Logger logger;
        @Autowired
        public JMSLogAdvice(Logger logger) {
            this.logger = logger;
        }
        @After("execution(* bank.integration.jms.*.*(..))")
        public void logJMSMessage(JoinPoint joinpoint) {
            logger.log("JMS Sender message sent: "+joinpoint.getArgs()[0]);
        }
}
