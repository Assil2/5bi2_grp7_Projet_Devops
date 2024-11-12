package tn.esprit.tpfoyer.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ConfigAOP {

    private static final Logger log = LoggerFactory.getLogger(ConfigAOP.class);

    @Before("execution(* tn.esprit.tpfoyer.service.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("In Method AOP : " + name);
    }

    @After("execution(* tn.esprit.tpfoyer.service.*.add*(..))")
    public void logMethodOut(JoinPoint joinPoint) {
        log.info("Execution Successful!");
    }

    @Around("execution(* tn.esprit.tpfoyer.service.*.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();

        Object obj = pjp.proceed();

        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Method execution time: " + elapsedTime + " milliseconds.");
        return obj;
    }
}
