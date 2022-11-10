package kr.co.study.api.common.engine.helper.context;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**   
 * @since       2018.10.03
 * @author      lucas
 * @description request helper
 **********************************************************************************************************************/
@Component
public class RequestHelper {
 
	public static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
}
