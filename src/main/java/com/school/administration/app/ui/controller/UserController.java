package com.school.administration.app.ui.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.administration.app.SpringApplicationContext;
import com.school.administration.app.io.repositories.InvoiceRepository;
import com.school.administration.app.io.repositories.QrenNotifRepositories;
import com.school.administration.app.io.repositories.TransactionRepository;
import com.school.administration.app.io.repositories.UserRepository;
import com.school.administration.app.security.SecurityConstant;
import com.school.administration.app.service.AddressService;
import com.school.administration.app.service.InvoiceService;
import com.school.administration.app.service.RoleService;
import com.school.administration.app.service.UserService;
import com.school.administration.app.shared.dto.HistoryDto;
import com.school.administration.app.shared.dto.InvoiceDto;
import com.school.administration.app.shared.dto.ProductsDto;
import com.school.administration.app.shared.dto.QrenNotifDto;
import com.school.administration.app.shared.dto.RoleDto;
import com.school.administration.app.shared.dto.UserDto;
import com.school.administration.app.ui.io.entity.InvoiceEntity;
import com.school.administration.app.ui.io.entity.QrenNotifEntity;
import com.school.administration.app.ui.io.entity.TransactionEntity;
import com.school.administration.app.ui.io.entity.UserEntity;
import com.school.administration.app.ui.model.contentResponse.ContentInvoice;
import com.school.administration.app.ui.model.contentResponse.ContentInvoices;
import com.school.administration.app.ui.model.contentResponse.ContentProduct;
import com.school.administration.app.ui.model.contentResponse.ContentProducts;
import com.school.administration.app.ui.model.contentResponse.ContentRole;
import com.school.administration.app.ui.model.contentResponse.ContentUser;
import com.school.administration.app.ui.model.contentResponse.ContentUsers;
import com.school.administration.app.ui.model.request.InvoiceRequestModel;
import com.school.administration.app.ui.model.request.ProductRequestModel;
import com.school.administration.app.ui.model.request.QrenNotifRequestModel;
import com.school.administration.app.ui.model.request.RoleRequestModel;
import com.school.administration.app.ui.model.request.UserDetailRequestModel;
import com.school.administration.app.ui.model.request.UserRequestModel;
import com.school.administration.app.ui.model.response.InvoiceResponse;
import com.school.administration.app.ui.model.response.UserResponse;

import com.school.administration.app.ui.model.response.ProductResponse;
import com.school.administration.app.ui.model.response.RoleResponse;

@RestController
@CrossOrigin(origins = {"http://localhost:3000/", "https://sangbango-project.web.app/"})
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	AddressService addressService;

	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping
	public String hello() {
		return "Welcome to my channel!";
	}
	
	@PostMapping(
		path = "/user-registration",
		consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
		produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
		)
	public ContentUser createUser(
			@RequestBody UserRequestModel user) throws Exception {
		UserResponse returnValue = new UserResponse();
		
		ContentUser result = new ContentUser();
		
		if (user.getEmail().isEmpty()) throw new NullPointerException("Username may not be null");
		if (user.getPassword().isEmpty()) throw new NullPointerException("Password may not be null");
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
		UserDto createdUser = userService.createUser(userDto);
		returnValue = modelMapper.map(createdUser, UserResponse.class);
		
		result.setContent(returnValue);
		result.setErrorCode("201");
		result.setErrorDesc("created");
		
		return result;
	}
	
	@PostMapping(
			path = "/roles",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
		public ContentRole createRoles(
				@RequestBody RoleRequestModel role) throws Exception {
			RoleResponse returnValue = new RoleResponse();
			
			ContentRole result = new ContentRole();
			
			if (role.getRoleName().isEmpty()) throw new NullPointerException("Role name may not be null");
			if (role.getRoleId().isEmpty()) throw new NullPointerException("Role id may not be null");
			
			ModelMapper modelMapper = new ModelMapper();
			RoleDto roleDto = modelMapper.map(role, RoleDto.class);
			
			RoleDto createdRole = roleService.createRole(roleDto);
			returnValue = modelMapper.map(createdRole, RoleResponse.class);
			
			result.setContent(returnValue);
			result.setErrorCode("201");
			result.setErrorDesc("created");
			
			return result;
		}
	
	@GetMapping(
			path = "/get-all-users", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ContentUsers getUsers() {
		List<UserResponse> returnValue = new ArrayList<>();
		
		ContentUsers result = new ContentUsers();
		
		List<UserDto> users = userService.getUsers();
		
		if (users != null && !users.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<UserResponse>>() {
			}.getType();
			returnValue = new ModelMapper().map(users, listType);
		}
		
		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get users");
		
		return result;
	}
	
	@GetMapping(path = "/get-user", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ContentUser getUserDetail(@RequestParam(value = "userId") String userId) {

		UserResponse returnValue = new UserResponse();
		
		ContentUser result = new ContentUser();
		
		UserDto userDto = userService.getUserByUserId(userId);
		
		if (userDto != null) {
			java.lang.reflect.Type listType = new TypeToken<UserResponse>() {
			}.getType();
			returnValue = new ModelMapper().map(userDto, listType);
		}

		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get user");
		
		return result;
	}
	
	@PutMapping(path = "/update-detail-user",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
		public ContentUser updateDetailUser(@RequestParam(value = "userId") String userId,
				@RequestBody UserDetailRequestModel userDetail) {
			UserResponse returnValue = new UserResponse();
			
			ContentUser result = new ContentUser();
			
			ModelMapper modelMapper = new ModelMapper();
			UserDto userDto = modelMapper.map(userDetail, UserDto.class);
			
			UserDto updatedDetailUser = userService.updateDetailUser(userId, userDto);
			returnValue = modelMapper.map(updatedDetailUser, UserResponse.class);
			
			result.setContent(returnValue);
			result.setErrorCode("0");
			result.setErrorDesc("success update detail user");
			
			return result;	
		}
	
	@PutMapping(path = "/disactivate-user",
		consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
		produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
		)
	public ContentUser disactivateUser(@RequestParam(value = "userId") String userId) {
		UserResponse returnValue = new UserResponse();
		
		ContentUser result = new ContentUser();
		
		UserDto userDto = new UserDto();
		
		UserDto disactivateUser = userService.disactiveUser(userId, userDto);
		BeanUtils.copyProperties(disactivateUser, returnValue);
		
		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success disactivated");
		
		return result;	
	}
	
	@PostMapping(
		path = "/create-product",
		consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
		produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
		)
	public ContentProduct createProduct(
			@RequestBody ProductRequestModel product) throws Exception {
		ProductResponse returnValue = new ProductResponse();
		
		ContentProduct result = new ContentProduct();
		
		ModelMapper modelMapper = new ModelMapper();
		ProductsDto productDto = modelMapper.map(product, ProductsDto.class);
		
		ProductsDto createdProduct = userService.createProduct(productDto);
		returnValue = modelMapper.map(createdProduct, ProductResponse.class);
		
		result.setContent(returnValue);
		result.setErrorCode("201");
		result.setErrorDesc("created");
		
		return result;
	}
	
	@GetMapping(
			path = "/get-all-products", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ContentProducts getProducts(@RequestParam(value = "page", defaultValue = "0") int page,
								   @RequestParam(value = "limit", defaultValue = "25") int limit) {
		List<ProductResponse> returnValue = new ArrayList<>();
		
		ContentProducts result = new ContentProducts();
		
		List<ProductsDto> products = userService.getProducts(page, limit);
		
		if (products != null && !products.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<ProductResponse>>() {
			}.getType();
			returnValue = new ModelMapper().map(products, listType);
		}
		
		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get products");
		
		return result;
	}
	
	@GetMapping(path = "/get-product", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ContentProduct getProductDetail(@RequestParam(value = "productId") String productId) {

		ProductResponse returnValue = new ProductResponse();
		
		ContentProduct result = new ContentProduct();
		
		ProductsDto productDto = userService.getProductByProductId(productId);
		
		if (productDto != null) {
			java.lang.reflect.Type listType = new TypeToken<ProductResponse>() {
			}.getType();
			returnValue = new ModelMapper().map(productDto, listType);
		}
		
		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get product");

		return result;
	}
	
	@PostMapping(
		path = "/create-invoice",
		consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
		produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
		)
	public InvoiceResponse createInvoice(
			InvoiceRequestModel invoice,
			@RequestParam String userId, String productId ) throws Exception {
		InvoiceResponse returnValue = new InvoiceResponse();
		
		ModelMapper modelMapper = new ModelMapper();
		InvoiceDto invoiceDto = modelMapper.map(invoice, InvoiceDto.class);
		
		InvoiceDto createdInvoice = invoiceService.createInvoice(userId, productId, invoiceDto);
		returnValue = modelMapper.map(createdInvoice, InvoiceResponse.class);
		
		return returnValue;
	}
	
	@GetMapping(
			path = "/get-all-invoices", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ContentInvoices getInvoices(@RequestParam(value = "page", defaultValue = "0") int page,
								   @RequestParam(value = "limit", defaultValue = "25") int limit) {
		List<InvoiceResponse> returnValue = new ArrayList<>();
		
		ContentInvoices result = new ContentInvoices();
		
		List<InvoiceDto> invoices = invoiceService.getInvoices(page, limit);
		
		if (invoices != null && !invoices.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<InvoiceResponse>>() {
			}.getType();
			returnValue = new ModelMapper().map(invoices, listType);
		}
		
		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get invoices");
		
		return result;
	}
	
	@GetMapping(
			path = "/get-all-invoices-user", 
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ContentInvoices getInvoicesUser(@RequestParam(value = "userId") String user) {
		List<InvoiceResponse> returnValue = new ArrayList<>();
		
		ContentInvoices result = new ContentInvoices();
		
		List<InvoiceDto> invoices = invoiceService.getInvoicesUser(user);
		
		if (invoices != null && !invoices.isEmpty()) {
			java.lang.reflect.Type listType = new TypeToken<List<InvoiceResponse>>() {
			}.getType();
			returnValue = new ModelMapper().map(invoices, listType);
		}
		
		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get invoices");
		
		return result;
	}
	
	@GetMapping(path = "/get-invoice", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ContentInvoice getInvoiceDetail(@RequestParam(value = "invoiceId") String invoiceId) {

		InvoiceResponse returnValue = new InvoiceResponse();
		
		ContentInvoice result = new ContentInvoice();
		
		InvoiceDto invoiceDto = invoiceService.getInvoiceByInvoiceId(invoiceId);
		
		if (invoiceDto != null) {
			java.lang.reflect.Type listType = new TypeToken<InvoiceResponse>() {
			}.getType();
			returnValue = new ModelMapper().map(invoiceDto, listType);
		}

		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get invoice");
		
		return result;
	}
	
	@GetMapping(path = "/get-content", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ContentInvoice getContent(@RequestParam(value = "invoiceId") String invoiceId) {

		InvoiceResponse returnValue = new InvoiceResponse();
		
		ContentInvoice result = new ContentInvoice();
		
		InvoiceDto invoiceDto = invoiceService.getContentByInvoiceId(invoiceId);
		
		if (invoiceDto != null) {
			java.lang.reflect.Type listType = new TypeToken<InvoiceResponse>() {
			}.getType();
			returnValue = new ModelMapper().map(invoiceDto, listType);
		}

		result.setContent(returnValue);
		result.setErrorCode("0");
		result.setErrorDesc("success get content");
		
		return result;
	}
	
	@GetMapping(path = "/get-transaction-history", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public void getTransactionHistory(HttpServletResponse res) 
			throws IOException, ServletException{
		try 
		{
			HistoryDto history = new HistoryDto();
			history.setIdTmoney("195281683222");
			history.setIdFusion("+6219564077581");
			history.setToken("aeff96eed2305248be46cc2661416ef9497258dfec7849a4eeffcca4d4e53a420ab60dbddf76fe70");
			history.setStartDate("2018-12-01 00:00:00");
			history.setStopDate("2020-12-01 23:59:59");
			history.setTransactionType("ALL_TRANSACTION");
			history.setMerchantId(32289);
			history.setPage(1);
			history.setMax(1000);
			
			ObjectMapper obj = new ObjectMapper();
			
			String url = "http://tmoney-qren-backend-tmoney-qren-prod.vsan-apps.playcourt.id/paybyqr/history-transaction";
			
			String json = obj.writeValueAsString(history);
			
			URL uri = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", 
					"Basic dG1vbmV5OmZmODY2ZjViNjE1NGJiYjdkOTc4ZTUyNDNiNDkzMjBiMGQxYWQ2N2M=");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			OutputStream os = connection.getOutputStream();
			os.write(json.getBytes("UTF-8"));
			os.close();
			
			InputStream in = new BufferedInputStream(connection.getInputStream());
			String result = IOUtils.toString(in, "UTF-8");
			
//			System.out.println(result);
			
			JSONObject response = new JSONObject(result);
			
			res.setStatus(HttpServletResponse.SC_OK);
    		res.setContentType("application/json");
    		res.getWriter().println(response);
    		res.getWriter().flush();
    		res.getWriter().close();
//			System.out.println(response);
			
			in.close();
			
			connection.disconnect();
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
	}
	
	@GetMapping(path = "/isTokenExpired", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public void isTokenExpired(Authentication authentication, HttpServletRequest request, HttpServletResponse res) 
			throws IOException, ServletException{
		
		UserRepository userRepository = (UserRepository) SpringApplicationContext.getBean("userRepository");
		String token = request.getHeader(SecurityConstant.HEADER_STRING);
		
//		System.out.println(token);		
        
        if (token != null) {
        	String currentUser = authentication.getPrincipal().toString();
            UserEntity userEntity = userRepository.findTokenByUsername(currentUser);
        	String token_DB = userEntity.getToken();
        	
            if (token.equals(token_DB)) {
            	JSONObject response = new JSONObject("{status_token: token active}");
//            	System.out.println(response);
        		res.setStatus(HttpServletResponse.SC_OK);
        		res.setContentType("application/json");
        		res.getWriter().println(response);
        		res.getWriter().flush();
        		res.getWriter().close();
			} else if (!token.equals(token_DB)){
				JSONObject response = new JSONObject("{status_token: token active, status_login: forbidden}");
//				System.out.println(response);
	    		res.setStatus(HttpServletResponse.SC_OK);
	    		res.setContentType("application/json");
	    		res.getWriter().println(response);
	    		res.getWriter().flush();
	    		res.getWriter().close();
			}
        }		
	}
	
	@GetMapping(path = "/get-qr-transaction", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public void getQrTransaction(HttpServletResponse res) 
			throws IOException, ServletException{
		try 
		{
			HistoryDto history = new HistoryDto();
			history.setIdTmoney("195281683222");
			history.setIdFusion("+6219564077581");
			history.setToken("aeff96eed2305248be46cc2661416ef9497258dfec7849a4eeffcca4d4e53a420ab60dbddf76fe70");
			history.setStartDate("2018-12-01 00:00:00");
			history.setStopDate("2020-12-01 23:59:59");
			history.setTransactionType("QR_PAYMENT");
			history.setMerchantId(32289);
			history.setPage(1);
			history.setMax(1000);
			
			ObjectMapper obj = new ObjectMapper();
			
			String url = "http://tmoney-qren-backend-tmoney-qren-prod.vsan-apps.playcourt.id/paybyqr/history-transaction";
			
			String json = obj.writeValueAsString(history);
			
			URL uri = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
			
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", 
					"Basic dG1vbmV5OmZmODY2ZjViNjE1NGJiYjdkOTc4ZTUyNDNiNDkzMjBiMGQxYWQ2N2M=");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			OutputStream os = connection.getOutputStream();
			os.write(json.getBytes("UTF-8"));
			os.close();
			
			InputStream in = new BufferedInputStream(connection.getInputStream());
			String result = IOUtils.toString(in, "UTF-8");
			
//			System.out.println(result);
			
			JSONObject response = new JSONObject(result);
			
			res.setStatus(HttpServletResponse.SC_OK);
    		res.setContentType("application/json");
    		res.getWriter().println(response);
    		res.getWriter().flush();
    		res.getWriter().close();
//			System.out.println(response);
			
			in.close();
			
			connection.disconnect();
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
	}
	
	@PostMapping(
			path = "/qren_payment_notification",
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
			)
		public void getPaymentNotification(@RequestBody QrenNotifRequestModel qrenNotif, HttpServletResponse res) throws Exception 
		{
			TransactionRepository transactionRepository = (TransactionRepository) SpringApplicationContext.getBean("transactionRepository");
			InvoiceRepository invoiceRepository = (InvoiceRepository) SpringApplicationContext.getBean("invoiceRepository");
		
			QrenNotifEntity qrenNotifEntity = new QrenNotifEntity();
			TransactionEntity transactionEntity = transactionRepository.findByTransactionId(qrenNotif.getTrxId());
			InvoiceEntity invoiceEntity = invoiceRepository.findInvoiceByInvoiceId(qrenNotif.getInvoice());
			
			System.out.println(transactionEntity.getTransactionId()+ " transaction");
			System.out.println(invoiceEntity.getInvoiceId()+ " invoice");
			
			invoiceEntity.setIsPayment(true);
			invoiceEntity.setModifiedBy("System");
			
			qrenNotifEntity.setInvoiceId(qrenNotif.getInvoice());
			qrenNotifEntity.setStatus(qrenNotif.getStatus());
			if (qrenNotif.getStatus() == "0") {
				transactionEntity.setStatus("Success");
			} else {
				transactionEntity.setStatus("Gagal");
			}
			
			qrenNotifEntity.setAmount(qrenNotif.getAmount());
			transactionEntity.setAmount(qrenNotif.getAmount());
			qrenNotifEntity.setMerchantApiKey(qrenNotif.getMerchantApiKey());
			qrenNotifEntity.setTrxId(qrenNotif.getTrxId());
			qrenNotifEntity.setQrenTransId(qrenNotif.getQrentransid());
			qrenNotifEntity.setMessage(qrenNotif.getMessage());
			
			String trxId = qrenNotif.getTrxId();
			String[] parts = trxId.split("-");
			String userId = parts[1];
			
			qrenNotifEntity.setUserId(userId);
			
			final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
			Calendar currentTime = Calendar.getInstance();
			String timeStamp = formatter.format(currentTime.getTime());
			
			qrenNotifEntity.setTimeStamp(timeStamp);
			transactionEntity.setSolvedDate(timeStamp);
			invoiceEntity.setModifiedDate(timeStamp);
			
			QrenNotifRepositories qrenNotifRepository = (QrenNotifRepositories) SpringApplicationContext.getBean("qrenNotifRepositories");
			qrenNotifRepository.save(qrenNotifEntity);
			
			transactionRepository.save(transactionEntity);
			invoiceRepository.save(invoiceEntity);
			
			final String CREATED_DATE = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat format = new SimpleDateFormat(CREATED_DATE);
			format.setTimeZone(TimeZone.getTimeZone("GMT+7"));
			
			Calendar cal = Calendar.getInstance();
			
			String responseTimeStamp = format.format(cal.getTime());
			
			QrenNotifDto qrenNotifDto = new QrenNotifDto();
			qrenNotifDto.setStatus("0");
			qrenNotifDto.setMessage("payment success");
			qrenNotifDto.setTimeStamp(responseTimeStamp);
			
			// Creating Object of ObjectMapper define in Jakson Api 
	        ObjectMapper Obj = new ObjectMapper(); 
	  
	        try 
	        { 
	            // set object as a json string 
	            String response = Obj.writeValueAsString(qrenNotifDto);
	                     
	            res.setStatus(HttpServletResponse.SC_OK);
	    		res.setContentType("application/json");
	    		res.getWriter().println(response);
	    		res.getWriter().flush();
	    		res.getWriter().close();
	    		
//	    		System.out.println(response);
	        } 
	        catch (IOException e) 
	        { 
	            e.printStackTrace(); 
	        }       
		}
}
