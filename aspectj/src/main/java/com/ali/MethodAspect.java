package com.ali;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MethodAspect {

    @Pointcut("@annotation(executionCount)")
    public void executionCountPointcut(ExecutionCount executionCount) {
    }

    @Pointcut("@annotation(completionTime)")
    public void completionTimePointcut(ExecutionTime completionTime) {
    }

    @Around("executionCountPointcut(executionCount)")
    public Object aroundExecutionCount(ProceedingJoinPoint joinPoint, ExecutionCount executionCount) throws Throwable {
        // Increment the count for the method identified by executionCount.id()
        System.out.println("Method executed: " + executionCount.id());
        return joinPoint.proceed();
    }

    @Around("completionTimePointcut(completionTime)")
    public Object aroundCompletionTime(ProceedingJoinPoint joinPoint, ExecutionTime completionTime) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Method " + completionTime.id() + " completed in " + duration + " ms");
        if (duration > completionTime.maxAllowedTime()) {
            System.out.println("Warning: Method " + completionTime.id() + " exceeded the maximum allowed time of " + completionTime.maxAllowedTime() + " ms");
        }
        return result;
    }
}
