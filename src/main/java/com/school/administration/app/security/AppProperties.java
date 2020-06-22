package com.school.administration.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class AppProperties {
	@Autowired
	private Environment env;
	
	public String getTokenSecret() {
		return env.getProperty("tokenSecret");
	}
}
