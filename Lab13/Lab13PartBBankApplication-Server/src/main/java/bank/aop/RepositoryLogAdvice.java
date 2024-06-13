package bank.aop;

import bank.integration.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Configuration
public class RepositoryLogAdvice {
    Logger logger;
    @Autowired
    public RepositoryLogAdvice(Logger logger) {
        this.logger = logger;
    }

    @Before("execution(* bank.repository.*.*(..))")
        public void logDAOCall(JoinPoint joinpoint) {
            logger.log("Before execution of method: "+joinpoint.getSignature().getName());
        }
}
