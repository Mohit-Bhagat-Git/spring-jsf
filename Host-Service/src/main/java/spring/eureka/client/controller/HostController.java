package spring.eureka.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hostServiceController")
public class HostController {
	
	@RequestMapping("/aliveCheck")
	public String aliveCheck(){
		
		return "host is alive";
	}
	
	

}
