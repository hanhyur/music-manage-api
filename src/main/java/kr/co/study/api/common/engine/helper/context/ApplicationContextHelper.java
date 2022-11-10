package kr.co.study.api.common.engine.helper.context;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**   
 * @since       2018.10.03
 * @author      lucas
 * @description application context helper
 **********************************************************************************************************************/
@Component
@RequiredArgsConstructor
public class ApplicationContextHelper {

	public static <T> T getInstance(Class<T> clazz){
		return applicationContext.getBean(clazz);
	}	
	
	private static final ApplicationContext applicationContext = null;
}
