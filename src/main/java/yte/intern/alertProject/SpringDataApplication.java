package yte.intern.alertProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import yte.intern.alertProject.model.ScheduledAlertRunnable;
import yte.intern.alertProject.services.ResponseService;

@SpringBootApplication
public class SpringDataApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);

	}

}
