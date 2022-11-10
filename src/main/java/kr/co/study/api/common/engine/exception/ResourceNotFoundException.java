package kr.co.study.api.common.engine.exception;

import kr.co.study.api.common.engine.exception.common.ExceptionCode;

/**
 * @since       2022.11.04
 * @author      aslan
 * @description resource not found exception(요청한 리소스를 찾을 수 없을 때 발생하는 예외)
 **********************************************************************************************************************/
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(){
		super(ExceptionCode.E00100007.name());
	}

	public ResourceNotFoundException(ExceptionCode exceptionCode){
		super(exceptionCode.name());
	}

	public ResourceNotFoundException(ExceptionCode exceptionCode, Exception exception){
		super(exceptionCode.name(), exception);
	}

}
