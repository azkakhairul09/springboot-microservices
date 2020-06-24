package com.school.administration.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.school.administration.app.io.repositories.InvoiceRepository;
import com.school.administration.app.io.repositories.ProductsRepository;
import com.school.administration.app.shared.dto.InvoiceDto;
import com.school.administration.app.shared.dto.ProductsDto;
import com.school.administration.app.ui.io.entity.InvoiceEntity;
import com.school.administration.app.ui.io.entity.ProductsEntity;

@Component
public class ScheduledTasks {
//	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    @Autowired
    ProductsRepository productRepository;
    
    @Autowired
    InvoiceRepository invoiceRepository;
    
    @Scheduled(cron = "0 0 6 * * *")
	public ProductsDto updateExpiredProduct() {
    	ProductsDto returnValue = new ProductsDto();
    	
    	final String DATE_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		 
		Calendar currentTime = Calendar.getInstance();
		 
		String date = formatter.format(currentTime.getTime());
		
		List<ProductsEntity> productEntity = new ArrayList<ProductsEntity>();
		productEntity = productRepository.findProductByExpiredDate(date);
		
		for (ProductsEntity product : productEntity)
		{
			if (product.getIsExpired() == false) 
			{
				product.setIsExpired(true);
				
				final String Date_Format = "yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat date_formatter = new SimpleDateFormat(Date_Format);
				date_formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
				 
				Calendar current_time = Calendar.getInstance();
				 
				String modifiedDate = date_formatter.format(current_time.getTime());
				product.setModifiedDate(modifiedDate);
				product.setModifiedBy("System");
				
				ProductsEntity updateProduct = productRepository.save(product);
				
				BeanUtils.copyProperties(updateProduct, returnValue);
			}
		}
		
//    	logger.info(dateFormat.format(new Date()));
    	
		return returnValue;
	}
    
    @Scheduled(cron = "* * * * * *")
	public InvoiceDto checkExpiredInvoice() {
    	InvoiceDto returnValue = new InvoiceDto();
    	
    	final String DATE_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		 
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		 
		String date = formatter.format(cal.getTime());
		
		List<InvoiceEntity> invoiceEntity = new ArrayList<InvoiceEntity>();
		Boolean expired = false;
		invoiceEntity = invoiceRepository.findInvoiceByInvoiceDateAndIsExpired(date, expired);
		
		for (InvoiceEntity invoice : invoiceEntity)
		{
			System.out.println(invoice);
			if (invoice.getIsExpired() == false) 
			{
				invoice.setIsExpired(true);
				
				final String Date_Format = "yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat date_formatter = new SimpleDateFormat(Date_Format);
				date_formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
				 
				Calendar current_time = Calendar.getInstance();
				 
				String modifiedDate = date_formatter.format(current_time.getTime());
				invoice.setModifiedDate(modifiedDate);
				invoice.setModifiedBy("System");
				
				InvoiceEntity updateInvoice = invoiceRepository.save(invoice);
				
				BeanUtils.copyProperties(updateInvoice, returnValue);
			}
		}
		
//    	logger.info(dateFormat.format(new Date()));
    	
		return returnValue;
	}
    
    
    @Scheduled(cron = "* * * * * *")
    public InvoiceDto updateInvoice() {
    	InvoiceDto returnValue = new InvoiceDto();
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.HOUR_OF_DAY, -1);
    	Date date = calendar.getTime();
    	SimpleDateFormat sdfStopTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String newStopTime = sdfStopTime.format(date);
    	
		List<InvoiceEntity> invoiceEntity = new ArrayList<InvoiceEntity>();
		invoiceEntity = invoiceRepository.findInvoiceByCreatedDate(newStopTime);
    	
		for (InvoiceEntity invoice : invoiceEntity) 
		{
			if (invoice.getIsPayment() == false && invoice.getIsExpired() == false) 
			{
				invoice.setIsExpired(true);
				
				final String Date_Format = "yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat date_formatter = new SimpleDateFormat(Date_Format);
				date_formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
				 
				Calendar current_time = Calendar.getInstance();
				 
				String modifiedDate = date_formatter.format(current_time.getTime());
				invoice.setModifiedDate(modifiedDate);
				invoice.setModifiedBy("System");
				
				InvoiceEntity updateInvoice = invoiceRepository.save(invoice);
				
				BeanUtils.copyProperties(updateInvoice,  returnValue);
			}
		}
		
    	return returnValue;	
    }
}
