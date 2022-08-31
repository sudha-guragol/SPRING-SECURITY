package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
//this annotation enable oauth security
@EnableOAuth2Sso
public class SpringBootOAuthApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOAuthApp1Application.class, args);
	}

}
