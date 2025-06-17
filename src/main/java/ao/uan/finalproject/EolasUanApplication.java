package ao.uan.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EolasUanApplication {

	public static void main(String[] args) {
		SpringApplication.run(EolasUanApplication.class, args);
	}

}
