package kr.co.study.api.common.engine.helper.exception;

import kr.co.study.api.common.engine.exception.common.ExceptionCode;
import kr.co.study.api.common.engine.exception.common.ExceptionForm;
import kr.co.study.api.common.engine.helper.message.MessageHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * @since       2020.02.24
 * @author      lucas
 * @description exception helper
 **********************************************************************************************************************/
@Slf4j
public class ExceptionHelper {

	public static ExceptionForm getException(ExceptionCode exceptionCode){
		String exceptionMessage = MessageHelper.getMessage(exceptionCode);
		ExceptionForm exceptionForm    = ExceptionForm.builder()
				.code   (exceptionCode)
				.message(exceptionMessage)
				.build();

		printLog(exceptionForm);
		return exceptionForm;
	}

	public static ExceptionForm getException(String code){
		ExceptionCode exceptionCode    = ExceptionCode.valueOf(code);
		String exceptionMessage = MessageHelper.getMessage(exceptionCode);
		ExceptionForm exceptionForm    = ExceptionForm.builder()
				.code   (exceptionCode)
				.message(exceptionMessage)
				.build();

		printLog(exceptionForm);
		return exceptionForm;
	}

	private static void printLog(ExceptionForm exceptionForm){
		log.error(exceptionForm.toString());
	}
}