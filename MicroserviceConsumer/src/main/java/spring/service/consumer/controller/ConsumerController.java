package spring.service.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/aliveCheck")
	public String aliveCheck(){
		String url = "http://EUREKA-SERVICE/hostServiceController/aliveCheck";
		return restTemplate.getForObject(url, String.class);
	}
	
	@RequestMapping("/dbService/readAllAdmin")
	public String readAllAdmin(){
		String url = "http://EUREKA-SERVICE-DB/admin/readAll";
		return restTemplate.getForObject(url, String.class);
	}

}
