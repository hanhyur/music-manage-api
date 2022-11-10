package kr.co.study.api.common.engine.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**   
 * @since       2018.10.03
 * @author      lucas
 * @description properties configuration
 **********************************************************************************************************************/
@Data
@Component
@ConfigurationProperties(prefix="property")
public class PropertiesConfiguration {

	private String name = null;

	@Data
	public static class Api {
		private String endPoint = null;
	}
	
	@Data
	public static class Swagger {
		private Info    info    = null;
		private Contact contact = null;
		
		@Data
		public static class Info {
			private String title   = null;
			private String desc    = null;
			private String version = null;
		}
		
		@Data
		public static class Contact {
			private String name  = null;
			private String url   = null;
			private String email = null;
		}
	}

	@Data
	public static class Attach {
		private String storeLocation = null;
	}

	@Data
	public static class Integrate {
		private SearchEngine  searchEngine  = null;
		private Authorization authorization = null;

		@Data
		public static class SearchEngine {
			private String endPoint = null;
			private Map<String, String> headers  = null;
		}

		@Data
		public static class Authorization {
			private String endPoint    = null;
			private String denyAll     = null;
			private List<String> allowGrades = null;
		}
	}

	@Data
	public static class Publish {
		private Source       source  = null;
		private List<Target> targets = null;

		@Data
		public static class Source {
			private String host           = null;
			private String id             = null;
			private String identity       = null;
			private String path           = null;
			private Integer port           = null;
			private Integer connectTimeOut = null;
			private Integer sessionTimeOut = null;
		}

		@Data
		public static class Target {
			private String host = null;
			private Integer port = null;
			private String id   = null;
			private String path = null;
		}
	}


	private Api       api       = null;
	private Swagger   swagger   = null;
	private Attach    attach    = null;
	private Integrate integrate = null;
	private Publish   publish   = null;
}
