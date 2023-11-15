package com.farizo.superheroe.infrastructure;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Aspect
@Component
public class CallMetricCollectorLogAspect {
    private static final Logger logger = getLogger(CallMetricCollectorLogAspect.class);

    @Around("@annotation(CallMetricCollectorLog)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long initTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - initTime;
        logger.info("{}", String.join(" - ",
                "Signature : " + joinPoint.getSignature(),
                "Execution time: " + executionTime + "ms"));
        return proceed;
    }
}
