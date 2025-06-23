package sv.edu.udb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
		basePackages = "sv.edu.udb.repository",
		entityManagerFactoryRef = "entityManagerFactory" // Referencia explícita
)
@EntityScan(basePackages = "sv.edu.udb.modelo")
public class SpringApiRestApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringApiRestApplication.class, args);
	}
}