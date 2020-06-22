package com.school.administration.app.shared;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.school.administration.app.security.SecurityConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Utils {
	
	private final Random RANDOM = new SecureRandom();
//	private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final String NUMBER = "0123456789";
//	private final int ITERATIONS = 10000;
//	private final int KEY_LENGTH = 256;
	
	public String generateUserId(int length) {
		return generateRandomString(length);
	}
	
	public String generateEmailVerificationToken(String userId) {
		String token = Jwts.builder().setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstant.getTokenSecret())
				.compact();
		
		return token;
		
	}

	private String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		
		for (int i = 0; i < length; i++) {
			returnValue.append(NUMBER.charAt(RANDOM.nextInt(NUMBER.length())));
		}
		
		return new String(returnValue);
	}
	
	public static boolean hasTokenExpired(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SecurityConstant.getTokenSecret())
				.parseClaimsJws(token).getBody();
		
		Date tokenExpirationDate = claims.getExpiration();
		Date todayDate = new Date();
		
		return tokenExpirationDate.before(todayDate);
	}
	
	public String generatePasswordResetToken(String userId)
    {
        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.PASSWORD_RESET_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.getTokenSecret())
                .compact();
        return token;
    }

	public String generateAudienceId(int length) {
		// TODO Auto-generated method stub
		return generateRandomString(length);
	}

	public String generateAddressId(int length) {
		// TODO Auto-generated method stub
		return generateRandomString(length);
	}
	
	public String generateInvoiceId(int length) {
		// TODO Auto-generated method stub
		return generateRandomString(length);
	}
	
	public String generateTransactionId(int length) {
		// TODO Auto-generated method stub
		return generateRandomString(length);
	}
	
	public String generateProductId(int length) {
		// TODO Auto-generated method stub
		return generateRandomString(length);
	}
}
