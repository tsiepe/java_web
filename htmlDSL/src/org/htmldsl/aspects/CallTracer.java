package org.htmldsl.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class CallTracer {

	@Around("execution(* *.*(..))")
	public Object traceAnyCall(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Entering " + pjp.getSignature().toLongString());

		Object result = pjp.proceed();

		System.out.println("Leaving " + pjp.getSignature().toLongString());

		return result;
	}
}
