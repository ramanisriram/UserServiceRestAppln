package hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MySpringBoot implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBoot.class, args);
	}
	
	@Bean
	public HelloService getHelloService(){
		return new DefaultHelloService();
	}

	@Override
	public void run(String... args) throws Exception {
		getHelloService().hello();
	}
}