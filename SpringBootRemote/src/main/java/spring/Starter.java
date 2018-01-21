package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@EnableEurekaClient
@SpringBootApplication
//@ComponentScan(basePackages = "spring")
public class Starter {

	public static void main(String args[]) {

		ApplicationContext ctx = SpringApplication.run(Starter.class, args);

	}
}
