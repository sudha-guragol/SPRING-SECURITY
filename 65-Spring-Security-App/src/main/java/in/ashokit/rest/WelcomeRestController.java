package in.ashokit.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//authentication is there for this application(uname:user pawd: displayed in console)
public class WelcomeRestController {
	
	
	@GetMapping("/welcome")
	public String welcome()
	{
		return "Welcome to Ashok IT";
	}

}
