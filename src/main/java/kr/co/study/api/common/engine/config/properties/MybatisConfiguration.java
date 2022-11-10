package kr.co.study.api.common.engine.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**   
 * @since       2020.02.24
 * @author      lucas
 * @description mybatis configuration
 **********************************************************************************************************************/
@Data
@Component
@ConfigurationProperties(prefix="mybatis")
public class MybatisConfiguration {
	
	private String typeAliasesPackage;
}
