package br.com.fiap.readtoenrich;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReadtoenrichApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadtoenrichApplication.class, args);
	}

	@Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory(); // Replace ServletWebServerFactory instantiation with a concrete implementation
    }

}
