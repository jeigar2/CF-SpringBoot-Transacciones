package es.comepiedras.retosprogramacion;

import es.comepiedras.retosprogramacion.model.Account;
import es.comepiedras.retosprogramacion.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class CfSpringBootTransaccionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfSpringBootTransaccionesApplication.class, args);
	}

	@Bean
	CommandLineRunner init (AccountRepository accountRepository) {
		return args -> {
			if(!accountRepository.existsById(1L)) {
				accountRepository.save(new Account("Cuenta A", new BigDecimal("5000")));
			}
			if(!accountRepository.existsById(2L)) {
				accountRepository.save(new Account("Cuenta B", new BigDecimal("3000")));
			}
		};
	}
}
