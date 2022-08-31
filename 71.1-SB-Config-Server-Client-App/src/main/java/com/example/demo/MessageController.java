package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MessageController {
	//config server get that msg value from git repo
//directly injecting value (if msg value is not available then we want to send error msg,if msg value is available then inject that msg to msg variable
	@Value("${msg:Config Server is not working.. please Check!!}")
	private String msg;
	
	@GetMapping("/msg")
	public String getMessage()
	{
		return this.msg;
	}
}
