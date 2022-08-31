package in.ashokit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//used to create password encoder bean
public class AppConfig {

	@Bean   //used to represent customized bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
}
