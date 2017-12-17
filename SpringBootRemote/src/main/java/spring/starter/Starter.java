package spring.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "spring")
// we need component scan beause this Starter class is under spring.starter
// package and will search for components in sub packages of this package
// forExample spring.starter.abc, spring.starter.zyx so to avoid it we need to
// tell what is the base package name.

public class Starter {

	public static void main(String args[]) {

		ApplicationContext ctx = SpringApplication.run(Starter.class, args);

	}

}
