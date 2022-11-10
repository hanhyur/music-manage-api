package kr.co.study.api.common.engine.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @since       2018.10.03
 * @author      lucas
 * @description model identifier
 **********************************************************************************************************************/
@Aspect
@Component
public class ModelIdentifier {

    @Around("execution(* springfox.documentation.schema.DefaultTypeNameProvider.nameFor(..))")
    public Object doSomethingAround(ProceedingJoinPoint point) throws Throwable {

        Class argument = (Class)point.getArgs()[0];
        point.proceed();
        return argument.getName();
    }
}
