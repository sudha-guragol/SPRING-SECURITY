package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		/*BCryptPasswordEncoder bpe=new BCryptPasswordEncoder();
		String encode = bpe.encode("DEV@123");
		System.out.println(encode);
		*/
	}

}
