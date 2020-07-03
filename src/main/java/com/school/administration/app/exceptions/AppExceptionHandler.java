package com.school.administration.app.exceptions;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.school.administration.app.ui.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler {

	HttpServletRequest request;
	
	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, HttpServletRequest request) {
	
		String queryString = request.getRequestURI();
		
		Enumeration<String> parameterNames = request.getParameterNames();
		 
        while (parameterNames.hasMoreElements()) {
 
            String paramName = parameterNames.nextElement();
 
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                System.out.println(paramName + " : " +paramValue);
            }
 
        }
		
        if (queryString.equals("/login")) 
		{
			String errorCode = "err403";
			String status = "failed to login";
			HttpStatus oke = HttpStatus.valueOf(200);
			Date date = Calendar.getInstance().getTime();
			
			ErrorMessage errorMessage = new ErrorMessage(errorCode, status, ex.getLocalizedMessage(), date);
			
			return new ResponseEntity<>(errorMessage, oke);
		}
        else if (queryString.equals("/get-user") && queryString.equals("/get-all-users")) 
		{	
			String errorCode = "err91";
			String status = "failed get user";
			HttpStatus oke = HttpStatus.valueOf(200);
			Date date = Calendar.getInstance().getTime();
			
			ErrorMessage errorMessage = new ErrorMessage(errorCode, status, ex.getLocalizedMessage(), date);
			
			return new ResponseEntity<>(errorMessage, oke);	
		} 
		else if (queryString.equals("/user-registration")) 
		{
			String errorCode = "err41";
			String status = "failed create user";
			HttpStatus oke = HttpStatus.valueOf(200);
			Date date = Calendar.getInstance().getTime();
			
			ErrorMessage errorMessage = new ErrorMessage(errorCode, status, ex.getLocalizedMessage(), date);
			
			return new ResponseEntity<>(errorMessage, oke);	
		}
		else if (queryString.equals("/disactivate-user")) 
		{
			String errorCode = "err41";
			String status = "failed";
			HttpStatus oke = HttpStatus.valueOf(200);
			Date date = Calendar.getInstance().getTime();
			
			ErrorMessage errorMessage = new ErrorMessage(errorCode, status, ex.getLocalizedMessage(), date);
			
			return new ResponseEntity<>(errorMessage, oke);	
		}
		else if (queryString.equals("/update-detail-user")) 
		{
			String errorCode = "err41";
			String status = "failed update user";
			HttpStatus oke = HttpStatus.valueOf(200);
			Date date = Calendar.getInstance().getTime();
			
			ErrorMessage errorMessage = new ErrorMessage(errorCode, status, ex.getLocalizedMessage(), date);
			
			return new ResponseEntity<>(errorMessage, oke);	
		}
		else if (queryString.equals("/get-all-products") && queryString.equals("/get-product")) 
		{
			String errorCode = "err91";
			String status = "failed get product";
			HttpStatus oke = HttpStatus.valueOf(200);
			Date date = Calendar.getInstance().getTime();
			
			ErrorMessage errorMessage = new ErrorMessage(errorCode, status, ex.getLocalizedMessage(), date);
			
			return new ResponseEntity<>(errorMessage, oke);
		}
		else if (queryString.equals("/create-product")) 
		{
			String errorCode = "err41";
			String status = "failed create product";
			HttpStatus oke = HttpStatus.valueOf(200);
			Date date = Calendar.getInstance().getTime();
			
			ErrorMessage errorMessage = new ErrorMessage(errorCode, status, ex.getLocalizedMessage(), date);
			
			return new ResponseEntity<>(errorMessage, oke);
		}
		else if (queryString.equals("/audience-registration")) 
		{
			String errorCode = "err41";
			String status = "failed create user";
			HttpStatus oke = HttpStatus.valueOf(200);
			Date date = Calendar.getInstance().getTime();
			
			ErrorMessage errorMessage = new ErrorMessage(errorCode, status, ex.getLocalizedMessage(), date);
			
			return new ResponseEntity<>(errorMessage, oke);
		}
		else if (queryString.equals("/create-invoice")) 
		{
			String errorCode = "err41";
			String status = "failed create invoice";
			HttpStatus oke = HttpStatus.valueOf(200);
			Date date = Calendar.getInstance().getTime();
			
			ErrorMessage errorMessage = new ErrorMessage(errorCode, status, ex.getLocalizedMessage(), date);
			
			return new ResponseEntity<>(errorMessage, oke);
		}
		else if (queryString.equals("/roles")) 
		{
			String errorCode = "err41";
			String status = "failed create role";
			HttpStatus oke = HttpStatus.valueOf(200);
			Date date = Calendar.getInstance().getTime();
			
			ErrorMessage errorMessage = new ErrorMessage(errorCode, status, ex.getLocalizedMessage(), date);
			
			return new ResponseEntity<>(errorMessage, oke);
		}
		else if (queryString.equals("/get-all-invoices") && queryString.equals("/get-invoice") && queryString.equals("/get-all-invoices-user")) 
		{
			String errorCode = "err91";
			String status = "failed get invoice";
			HttpStatus oke = HttpStatus.valueOf(200);
			Date date = Calendar.getInstance().getTime();
			
			ErrorMessage errorMessage = new ErrorMessage(errorCode, status, ex.getLocalizedMessage(), date);
			
			return new ResponseEntity<>(errorMessage, oke);
		}
		else if (queryString.equals("/get-payment-notif")) 
		{
			String errorCode = "err91";
			String status = "failed get invoice";
			HttpStatus oke = HttpStatus.valueOf(200);
			Date date = Calendar.getInstance().getTime();
			
			ErrorMessage errorMessage = new ErrorMessage(errorCode, status, ex.getLocalizedMessage(), date);
			
			return new ResponseEntity<>(errorMessage, oke);
		}
		
		return null;		
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleOtherException(Exception ex, HttpServletRequest request) {
		
		String errorCode = "err99";
		String status = "failed";
		
		HttpStatus oke = HttpStatus.valueOf(200);
		Date date = Calendar.getInstance().getTime();
		
		ErrorMessage errorMessage = new ErrorMessage(errorCode, status, ex.getLocalizedMessage(), date);
		
		return new ResponseEntity<>(errorMessage, oke);
	}
}
