package com.stackroute.employeemongo.aspect;


import com.stackroute.employeemongo.domain.Employee;
import com.stackroute.employeemongo.exception.EmployeeExistsException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component

//core will create the object
public class LoggingAspect {

//Here I am going to work with loggers

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    //What action you want to take and at what point
    //Take some action before my saveEmployee method executes

    //do some actions aftre my saveEmployee get executes

    //if my method is throwing some exception log that as well

    //methods can have any access specifiers - private , public , defualt , protected
    //pointcuts designators

    //pointcut - is having regular expression

    @Before(value = "execution(* com.stackroute.employeemongo.controller.EmployeeController .*(..))")
    public void beforeAdvicemethod(JoinPoint joinPoint ){

        logger.info("inside the before advice");
        logger.info("Target method object" + joinPoint.getSignature().getName());
        /*logger.info("Object with following data will be persisted " + employee);*/
    }

    @After(value = "execution(* com.stackroute.employeemongo.controller.EmployeeController .*(..)) && args (employee)")
    public void afterAdvicemethod(JoinPoint joinPoint , Employee employee ){

        logger.info("inside the after  advice");
        logger.info("Target method object --->" + joinPoint.getSignature().getName());
        logger.info("Object with following data will be persisted " + employee);
    }

    @AfterReturning(value = "execution(* com.stackroute.employeemongo.controller.EmployeeController .*(..))" , returning = "retval")
    public void afterReturningAdvice(JoinPoint joinPoint , Object retval) {

        logger.info("inside the afterReturn  advice");
        logger.info("Target method object --->" + joinPoint.getSignature().getName());
        logger.info("Object with following data will be persisted " + retval);
    }



        @Pointcut(value = "execution(* com.stackroute.employeemongo.service.EmployeeServiceImpl.*(..))")
        private void logAfterException(){}

        @AfterThrowing(value = "logAfterException()" , throwing = "exception")
        public void afterThrowingAdvice(JoinPoint joinPoint , Exception exception){
        logger.error("Inside after throwing");
        logger.error("Target method object ---->" + joinPoint.getSignature().getName());
        logger.error("Exception thrown" + exception.getMessage());

        }
}
