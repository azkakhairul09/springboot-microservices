package com.school.administration.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.administration.app.SchoolAdministrationApplication;
import com.school.administration.app.SpringApplicationContext;
import com.school.administration.app.security.AppProperties;

@SpringBootApplication
@CrossOrigin(origins = {"*"})
@EnableScheduling
public class SchoolAdministrationApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SchoolAdministrationApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SchoolAdministrationApplication.class);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
	
	@Bean(name="AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}
	
//	@SuppressWarnings("rawtypes")
//	@Bean
//    public FilterRegistrationBean simpleCorsFilter() {  
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
//        CorsConfiguration config = new CorsConfiguration();  
//        config.setAllowCredentials(true); 
//        // *** URL below needs to match the Vue client URL and port ***
//        config.setAllowedOrigins(Collections.singletonList("http://localhost:3000/")); 
//        config.setAllowedMethods(Collections.singletonList("*"));  
//        config.setAllowedHeaders(Collections.singletonList("*"));  
//        source.registerCorsConfiguration("/**", config);  
//        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);  
//        return bean;  
//    }
}

@RestController
class HelloController {
	@GetMapping("/")
	String hello() {
		return "Hello World";
	}
}