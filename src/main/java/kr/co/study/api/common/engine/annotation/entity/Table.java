package kr.co.study.api.common.engine.annotation.entity;

import kr.co.study.api.common.engine.constant.Constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @since       2020.03.05
 * @author      lucas
 * @description table
 **********************************************************************************************************************/
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

	String value() default Constant.String.EMPTY;
}