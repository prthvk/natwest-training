package com.stackroute.keepnote.aspectj;

/* Annotate this class with @Aspect and @Component */

import com.stackroute.keepnote.controller.NoteController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	/*
	 * Write loggers for each of the methods of Note controller, any particular method
	 * will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */
	private Logger logger = LoggerFactory.getLogger(NoteController.class);

	@Before("execution(* com.stackroute.keepnote.controller.NoteController.*(..))")
	public void logBeforeCategory(JoinPoint point){
		logger.info("In before advice" + point.getSignature().getName());
	}

	@After("execution(* com.stackroute.keepnote.controller.NoteController.*(..))")
	public void logAfterCategory(JoinPoint point){
		logger.info("In after advice" + point.getSignature().getName());
	}

	@AfterReturning(value = "execution(* com.stackroute.keepnote.controller.NoteController.*(..))", returning = "retval")
	public void logAfterCategory(JoinPoint point, Object retval){
		logger.info("In after returning advice" + point.getSignature().getName());
		logger.info("Return value " + retval );
	}
	@AfterThrowing(value = "execution(* com.stackroute.keepnote.controller.NoteController.*(..))", throwing = "e")
	public void logAfterCategory(JoinPoint point, Exception e){
		logger.info("In after throwing advice" + point.getSignature().getName());
		logger.info("Exception thrown is " + e.getMessage() );
	}
}
