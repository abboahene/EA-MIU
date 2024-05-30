package customers.aop;

import customers.EmailSender;
import customers.EmailSenderImpl;
import customers.Logger;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class AspectClass {
    Logger logger;
    @Autowired
    public AspectClass(Logger logger) {
        this.logger = logger;
    }
    @After("execution(* customers.EmailSender.sendEmail(..))")
    public void logJMSMessage(JoinPoint joinPoint) {
        logger.log("method="+joinPoint.getSignature().getName()+ " address="+joinPoint.getArgs()[0]
                + " message="+joinPoint.getArgs()[1]
                        + " outgoing mail server=" + ((EmailSenderImpl)joinPoint.getTarget()).getOutgoingMailServer()
                );
    }
    @Around("execution(* customers.CustomerRepository.save(..))")
    public Object LogExecutionTime(ProceedingJoinPoint call ) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();

        long totalTime = sw.getTotalTimeMillis();

        logger.log("Execution time for "+ call.getSignature().getName() +" ="+ totalTime+"ms");

        return retVal;
    }
}
