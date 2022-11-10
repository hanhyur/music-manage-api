package kr.co.study.api.common.engine.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**  
 * @since       2018.10.11
 * @author      lucas
 * @description response mapper
 **********************************************************************************************************************/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface ResponseMapper {
	
	Class<?> value();
}  

