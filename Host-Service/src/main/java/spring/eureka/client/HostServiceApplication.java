package spring.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient//because we need to tell spring that this class is client not server. OR @EnableDiscoveryClient if we are using Consul instead of Eureka.
@SpringBootApplication
public class HostServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HostServiceApplication.class, args);
	}
}
