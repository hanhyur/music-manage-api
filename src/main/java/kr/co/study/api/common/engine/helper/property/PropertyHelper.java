package kr.co.study.api.common.engine.helper.property;

import kr.co.study.api.common.engine.config.properties.MybatisConfiguration;
import kr.co.study.api.common.engine.config.properties.PropertiesConfiguration;
import kr.co.study.api.common.engine.config.properties.SecurityConfiguration;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**   
 * @since       2018.10.03
 * @author      lucas
 * @description property helper 
 **********************************************************************************************************************/
@Component
public class PropertyHelper {
	
	@Autowired
    private PropertyHelper(PropertiesConfiguration propertiesConfiguration, MybatisConfiguration mybatisConfiguration, SecurityConfiguration securityConfiguration) {
        PropertyHelper.property = propertiesConfiguration;
        PropertyHelper.mybatis  = mybatisConfiguration;
        PropertyHelper.security = securityConfiguration;
    }
	
	@Getter
	private static PropertiesConfiguration property = null;

    @Getter
    private static MybatisConfiguration mybatis = null;

    @Getter
    private static SecurityConfiguration security = null;
}