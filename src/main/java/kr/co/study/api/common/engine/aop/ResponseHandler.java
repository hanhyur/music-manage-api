package kr.co.study.api.common.engine.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**  
 * @since       2018.10.11
 * @author      lucas
 * @description response handler
 **********************************************************************************************************************/
@ControllerAdvice(annotations={ResponseMapper.class})
@Deprecated
public class ResponseHandler {
    
}

