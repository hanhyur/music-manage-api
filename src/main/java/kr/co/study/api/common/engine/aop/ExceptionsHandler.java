package kr.co.study.api.common.engine.aop;

import kr.co.study.api.common.engine.constant.Constant;
import kr.co.study.api.common.engine.exception.common.ExceptionForm;
import kr.co.study.api.common.engine.helper.exception.ExceptionHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**  
 * @since       2020.02.24
 * @author      lucas
 * @description exception handler(reference site : http://onecellboy.tistory.com/346)
 **********************************************************************************************************************/
@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(SQLException.class)
	public ResponseEntity<?> handleSQLException(SQLException e){
		if(StringUtils.isNotEmpty(e.getMessage()) && StringUtils.startsWith(e.getMessage(), Constant.Message.ERROR)){
			ExceptionForm exceptionForm = ExceptionHelper.getException(e.getMessage());

			return new ResponseEntity<>(exceptionForm, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		}

		log.error(e.getMessage());

		return new ResponseEntity<>(e.getMessage(), HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
	}

	@ExceptionHandler
	public ResponseEntity<?> handleBadRequestException(Exception e) {
		return new ResponseEntity<>(ExceptionHelper.getException(e.getMessage()), HttpStatus.BAD_REQUEST);
	}

}