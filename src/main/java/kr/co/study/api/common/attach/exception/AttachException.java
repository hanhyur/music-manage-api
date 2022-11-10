package kr.co.study.api.common.attach.exception;

import kr.co.study.api.common.engine.exception.common.ExceptionCode;

/**
 * @since       2020.02.24
 * @author      lucas
 * @description attach exception
 **********************************************************************************************************************/
@SuppressWarnings("serial")
public class AttachException extends RuntimeException {

	public AttachException(){
		super(ExceptionCode.E00100100.name());
	}

	public AttachException(ExceptionCode exceptionCode){
		super(exceptionCode.name());
	}

	public AttachException(ExceptionCode exceptionCode, Exception exception){
		super(exceptionCode.name(), exception);
	}
}