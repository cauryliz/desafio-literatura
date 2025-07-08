package com.example.desafioliteratura;

import com.example.desafioliteratura.principal.Principal;
import com.example.desafioliteratura.repository.AutorRepository;
import com.example.desafioliteratura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.desafioliteratura.repository")
@EntityScan(basePackages = "com.example.desafioliteratura.model")
public class DesafioLiteraturaApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(DesafioLiteraturaApplication.class, args);


}
@Override
public void run(String... args) throws Exception{
	Principal principal = new Principal(libroRepository, autorRepository);
		principal.muestraElMenu();

}
}