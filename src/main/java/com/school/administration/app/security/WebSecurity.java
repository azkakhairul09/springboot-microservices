package com.school.administration.app.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.school.administration.app.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and()
		.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, SecurityConstant.USER_SIGN_UP_URL)
			.permitAll()
			.antMatchers(HttpMethod.POST, SecurityConstant.LOGIN)
			.permitAll()
			.antMatchers(HttpMethod.POST, SecurityConstant.ROLE)
			.permitAll()
			.antMatchers(HttpMethod.GET, SecurityConstant.HELLOWORLD)
			.permitAll()
			.antMatchers(HttpMethod.GET, SecurityConstant.VERIFICATION_EMAIL_URL)
			.permitAll()
			.antMatchers(HttpMethod.POST, SecurityConstant.PASSWORD_RESET_REQUEST_URL)
			.permitAll()
			.antMatchers(HttpMethod.POST, SecurityConstant.PASSWORD_RESET_URL)
			.permitAll()
			.anyRequest().authenticated().and()
			.addFilter(getAuthenticationFilter())
			.addFilter(new AuthorizationFilter(authenticationManager()));
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	public AuthenticationFilter getAuthenticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/login");
		
		return filter;
	}
	
	@Bean
	public CorsConfigurationSource configurationSource() {
		
		final CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "OPTIONS"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("*"));
		
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
}
