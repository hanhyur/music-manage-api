package kr.co.study.api.common.engine.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**   
 * @since       2020.03.11
 * @author      lucas
 * @description security configuration
 **********************************************************************************************************************/
@Data
@Component
@ConfigurationProperties(prefix="security")
public class SecurityConfiguration {

	private Oauth2 ouauth2 = null;

	@Data
	public static class Oauth2 {

		private Client   client   = null;
		private Resource resource = null;

		@Data
		public static class Client {
			private String grantType;
			private String clientId;
			private String clientSecret;
		}

		@Data
		public static class Resource {
			private String tokenInfoUri;
		}
	}
}
