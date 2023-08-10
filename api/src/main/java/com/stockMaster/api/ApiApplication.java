package com.stockMaster.api;

import com.stockMaster.api.controlador.ControladorProducto;
import com.stockMaster.api.modelo.RepositorioProducto;
import com.stockMaster.api.vista.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;


@SpringBootApplication
@ComponentScan("com.stockMaster.api.modelo")
@EnableJdbcRepositories("com.stockMaster.api.modelo")
public class ApiApplication {

	@Autowired
	RepositorioProducto repoProducto;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ApiApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}

	@Bean
	ApplicationRunner applicationRunner(){
		return args -> {
			Principal vista = new Principal();
			ControladorProducto controlador = new ControladorProducto(repoProducto, vista);
		};
	}
}
