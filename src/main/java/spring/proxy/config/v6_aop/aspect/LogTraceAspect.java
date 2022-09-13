package spring.proxy.config.v6_aop.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import spring.proxy.trace.TraceStatus;
import spring.proxy.trace.logtrace.LogTrace;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LogTraceAspect {

    private final LogTrace logTrace;

    @Around("execution(* spring.proxy.app..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        TraceStatus status = null;
        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            // 로직 호출
            Object result = joinPoint.proceed();
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
