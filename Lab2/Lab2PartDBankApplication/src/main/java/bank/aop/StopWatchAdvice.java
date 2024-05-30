package bank.aop;

import bank.integration.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class StopWatchAdvice {

    Logger logger;
    @Autowired
    public StopWatchAdvice(Logger logger) {
        this.logger = logger;
    }
    @Around("execution(* bank.service.*.*(..))")
    public Object methodTimer(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object proceed = joinPoint.proceed();
        stopWatch.stop();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        logger.log("Execution time of "+ className+"."+methodName+": "+ stopWatch.getTotalTimeMillis());
        return proceed;
    }
}
