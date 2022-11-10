package kr.co.study.api.common.engine.exception;

import kr.co.study.api.common.engine.exception.common.ExceptionCode;

/**
 * @since       2018.10.15
 * @author      lucas
 * @description bad request exception(비정상적 요청을 하였을 경우 발생되는 예외)
 **********************************************************************************************************************/
@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException {

	public BadRequestException(){
		super(ExceptionCode.E00100005.name());
	}
	
	public BadRequestException(ExceptionCode exceptionCode){
		super(exceptionCode.name());
	}
	
	public BadRequestException(ExceptionCode exceptionCode, Exception exception){
		super(exceptionCode.name(), exception);
	}
}
