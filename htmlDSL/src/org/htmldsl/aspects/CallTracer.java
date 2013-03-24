package org.htmldsl.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class CallTracer {

	@Around("execution(* *.*(..))")
	public Object traceAnyCall(ProceedingJoinPoint pjp) throws Throwable {
		Object result = null;
		System.out.println("About to enter "
				+ pjp.getSignature().toLongString());

		result = pjp.proceed();

		System.out.println("About to leave "
				+ pjp.getSignature().toLongString());

		return result;
	}
}
