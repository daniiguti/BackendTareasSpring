package com.gpv.springtareas.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"com.gpv.springtareas.demo.services", "com.gpv.springtareas.demo.controladores"}) //esta etiqueta busca ya las interfaces
                                                                                   //que extienden de JpaRepository, por eso
                                                                                   //dichas clases no es necesario anotarlas con @Service, @Component, etc
                                                                                   //para poder usarlas con @Autowired
@EnableJpaRepositories(basePackages = "com.gpv.springtareas.demo.repositories")  
public class ProyectoFullStackTareasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFullStackTareasApplication.class, args);
	}

}
