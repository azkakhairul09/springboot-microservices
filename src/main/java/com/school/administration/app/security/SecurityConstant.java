package com.school.administration.app.security;

import com.school.administration.app.SpringApplicationContext;

public class SecurityConstant {

	public static final long EXPIRATION_TIME = 1000*60*60*24;
	public static final long PASSWORD_RESET_EXPIRATION_TIME = 1000*60*60;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String QREN_NOTIF = "/qren_payment_notification";
	public static final String USER_SIGN_UP_URL = "/user-registration";
	public static final String QREN_NOTIF_DEV = "/qren_payment_notification_dev";
	public static final String GET_QREN_NOTIFICATION = "/get-payment-notif";
	public static final String ROLE = "/roles";
	public static final String LOGIN = "/login";
	public static final String HELLOWORLD = "/";
	public static final String VERIFICATION_EMAIL_URL = "/email-verification";
	public static final String PASSWORD_RESET_REQUEST_URL = "/password-reset-request";
	public static final String PASSWORD_RESET_URL = "/password-reset";

	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTokenSecret();
	}
	
}
