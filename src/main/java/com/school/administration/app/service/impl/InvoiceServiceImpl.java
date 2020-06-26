package com.school.administration.app.service.impl;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.administration.app.exceptions.UserServiceException;
import com.school.administration.app.io.repositories.InvoiceRepository;
import com.school.administration.app.io.repositories.ProductsRepository;
import com.school.administration.app.io.repositories.UserRepository;
import com.school.administration.app.service.InvoiceService;
import com.school.administration.app.shared.Utils;
import com.school.administration.app.shared.dto.InvoiceDto;
import com.school.administration.app.shared.dto.QrenInvoiceDto;
import com.school.administration.app.ui.io.entity.InvoiceEntity;
import com.school.administration.app.ui.io.entity.ProductsEntity;
import com.school.administration.app.ui.io.entity.UserEntity;

@Service
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductsRepository productsRepository;
	
	@Autowired
	InvoiceRepository invoiceRepository; 
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	Utils utils;
	
	@Override
	public InvoiceDto createInvoice(String userId, String productId, InvoiceDto invoice) {
		// TODO Auto-generated method stub	
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		InvoiceEntity invoiceEntity = modelMapper.map(invoice, InvoiceEntity.class);
		
		UserEntity userEntity = userRepository.findUserByUserId(userId);
	
		invoiceEntity.setUserId(userEntity);
		invoiceEntity.setUser(userId);
		
		ProductsEntity productEntity = productsRepository.findProductByProductId(productId);
		if(productEntity.getIsExpired() == true) throw new UserServiceException("product is expired");
		invoiceEntity.setProductId(productEntity);
		
		invoiceEntity.setInvoiceName("Invoice Pembayaran "+productEntity.getProductName());
		invoiceEntity.setNominal(productEntity.getPrice());
		invoiceEntity.setInfo(invoiceEntity.getInvoiceName());
		
		String transactionId = utils.generateTransactionId(5);
		
		invoiceEntity.setTransactionId(userEntity.getUsername()+transactionId);
		
		
//		System.out.println(request.getHeader("Authorization"));
		try {		
			
			QrenInvoiceDto qrenInvoiceDto = new QrenInvoiceDto();
			qrenInvoiceDto.setMerchantApiKey("195281683222");
			qrenInvoiceDto.setNominal(productEntity.getPrice());
			qrenInvoiceDto.setStaticQr("0");
			qrenInvoiceDto.setInvoiceName("Invoice Pembayaran "+productEntity.getProductName()+" a/n "+userEntity.getFullName());
			qrenInvoiceDto.setQrGaruda("1");
			qrenInvoiceDto.setInfo(invoiceEntity.getInvoiceName());
			qrenInvoiceDto.setTrxId(invoiceEntity.getTransactionId());
			
			ObjectMapper obj = new ObjectMapper();
			
			String url = "https://qren-api.tmoney.co.id/paybyqr/createinvoice/";
			
			String json = obj.writeValueAsString(qrenInvoiceDto);
			
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
			
			JSONObject qrenResponse = new JSONObject(result);
			invoiceEntity.setInvoiceId(qrenResponse.getString("invoiceId"));
			invoiceEntity.setQrContent(qrenResponse.getString("content"));
			
			in.close();
			
			connection.disconnect();
		
		} catch (Exception e) {
//			System.out.println(e);
		}
		
		final String DATE_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		 
		Calendar currentTime = Calendar.getInstance();
		 
		String timeStr = formatter.format(currentTime.getTime());
		
		invoiceEntity.setInvoiceDate(timeStr);
		invoiceEntity.setIsPayment(false);
		invoiceEntity.setIsExpired(false);
		
		invoiceEntity.setCreatedBy(authentication.getName());
		
		final String CREATED_DATE = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(CREATED_DATE);
		format.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		
		Calendar createdTime = Calendar.getInstance();
		
		String createdDate = format.format(createdTime.getTime());
		invoiceEntity.setCreatedDate(createdDate);
		
		InvoiceEntity invoiceDetail = invoiceRepository.save(invoiceEntity);
		
		InvoiceDto returnValue = modelMapper.map(invoiceDetail, InvoiceDto.class);
		
		BeanUtils.copyProperties(invoiceDetail, returnValue);
		
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InvoiceDto> getInvoices(int page, int limit) {
		// TODO Auto-generated method stub
		List<InvoiceDto> returnValue = new ArrayList<InvoiceDto>();
		
		ModelMapper modelMapper = new ModelMapper();
		
		if (page>0) page = page-1;
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<InvoiceEntity> invoicePage = invoiceRepository.findAll(pageableRequest);
		
		List<InvoiceEntity> invoices = invoicePage.getContent();
		
		for (InvoiceEntity invoiceEntity : invoices) {
			returnValue.add(modelMapper.map(invoiceEntity, InvoiceDto.class) );
		}
		
		return returnValue;
	}

	@Override
	public InvoiceDto getInvoiceByInvoiceId(String invoiceId) {
		// TODO Auto-generated method stub
		InvoiceDto returnValue = new InvoiceDto();
		
		ModelMapper modelMapper = new ModelMapper();
		
		InvoiceEntity invoiceEntity = invoiceRepository.findInvoiceByInvoiceId(invoiceId);
		
		if (invoiceEntity == null) throw new UserServiceException(
				"invoice not found");
		
		returnValue = modelMapper.map(invoiceEntity, InvoiceDto.class);
		
		return returnValue;
	}
	
	@Override
	public InvoiceDto getContentByInvoiceId(String invoiceId) {
		// TODO Auto-generated method stub
		InvoiceDto returnValue = new InvoiceDto();
		
		ModelMapper modelMapper = new ModelMapper();
		
		InvoiceEntity invoiceEntity = invoiceRepository.findContentByInvoiceId(invoiceId);
		
		if (invoiceEntity == null) throw new UserServiceException(
				"invoice not found");
		
		returnValue = modelMapper.map(invoiceEntity, InvoiceDto.class);
		
		return returnValue;
	}

	@Override
	public List<InvoiceDto> getInvoicesUser(String user) {
		// TODO Auto-generated method stub
		List<InvoiceDto> returnValue = new ArrayList<InvoiceDto>();
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<InvoiceEntity> invoiceEntity = invoiceRepository.findInvoiceByUser(user);
		
		if (invoiceEntity == null) throw new UserServiceException(
				"invoice not found");
		
		for (InvoiceEntity invoices : invoiceEntity) {
			returnValue.add(modelMapper.map(invoices, InvoiceDto.class) );
		}
		
		return returnValue;
	}
}
