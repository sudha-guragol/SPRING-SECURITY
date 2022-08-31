package in.ashokit.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class welcomeRestController {

	@GetMapping("/")
	public String welcomeMsg()
	{
		return "Welcome To Ashok IT";
	}
}