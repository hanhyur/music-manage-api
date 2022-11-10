package kr.co.study.api.common.engine.config.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

/**   
 * @since       2018.10.03
 * @author      lucas
 * @description schedule configuration
 **********************************************************************************************************************/
@Configuration 
@EnableScheduling 
@Profile({"prod", "dev"})
public class ScheduleConfiguration { 
	
}
