package spring.service.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviceConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceConsumerApplication.class, args);
	}
	
	@Configuration
	class Confg {
		
		@LoadBalanced
		@Bean
		public RestTemplate restTemplate(){
			return new RestTemplate();
		}
	}
}
