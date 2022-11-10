package kr.co.study;

import kr.co.study.api.common.attach.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**   
 * @since       2020.02.24
 * @author      lucas
 * @description backend application
 **********************************************************************************************************************/
@SpringBootApplication(exclude={UserDetailsServiceAutoConfiguration.class})
public class BackendApplication {

	@Autowired
	private AttachService attachService;

	@Bean
	public CommandLineRunner init() {
		return (args) -> {
			attachService.initialize();
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	} 
}
