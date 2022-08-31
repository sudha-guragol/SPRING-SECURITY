package in.ashokit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String getHomepage()
	{
		return "homePage";
	}
	
	@GetMapping("/welcome")
	public String getWelcomePage()
	{
		return "welcomePage";
	}

	
	@GetMapping("/admin ")
	public String getAdminpage()
	{
		return "adminPage";
	}

	
	@GetMapping("/emp")
	public String getEmppage()
	{
		return "empPage";
	}

	@GetMapping("/manager")
	public String getManagerpage()
	{
		return "managerPage";
	}
	
	@GetMapping("/common")
	public String getCommonpage()
	{
		return "commonPage";
	}

	@GetMapping("/accessDenied")
	public String getAccessDeniedpage()
	{
		return "accessDeniedPage";
	}


}
