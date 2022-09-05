package benter.tech.todoapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.DatabaseStartupValidator;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Stream;

@SpringBootApplication
public class TodoAppApplication {

	@Value("${todo.cors.allowed.origins}")
	private String allowedOrigin;

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

	/**
	 * Needed to retry to connect to the datasource when starting the application instead of instantly breaking.
	 * @return DatabaseStartupValidator
	 */
	@Bean
	public static BeanFactoryPostProcessor dependsOnPostProcessor() {
		return beanFactory -> {
			String[] jpa = beanFactory.getBeanNamesForType(EntityManagerFactory.class);
			Stream.of(jpa)
					.map(beanFactory::getBeanDefinition)
					.forEach(it -> it.setDependsOn("databaseStartupValidator"));
		};
	}

	/**
	 * Needed to retry to connect to the datasource when starting the application instead of instantly breaking.
	 * @return DatabaseStartupValidator
	 */
	@Bean
	public DatabaseStartupValidator databaseStartupValidator(DataSource dataSource) {
		var databaseStartupValidator = new DatabaseStartupValidator();
		databaseStartupValidator.setDataSource(dataSource);
		databaseStartupValidator.setValidationQuery(DatabaseDriver.MARIADB.getValidationQuery());
		return databaseStartupValidator;
	}


	/**
	 * Needed to get around cors
	 * @return WebMvcConfigurer
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins(allowedOrigin)
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("Access-Control-Allow-Headers", "Access-Control-Allow-Origin",
								"Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin",
								"Cache-Control", "Content-Type", "Authorization");
			}
		};
	}
}
