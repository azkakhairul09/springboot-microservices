package com.school.administration.app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.school.administration.app.shared.dto.InvoiceDto;

public interface InvoiceService extends UserDetailsService {
	InvoiceDto createInvoice(String userId, String ProductId, InvoiceDto invoice);
	List<InvoiceDto> getInvoices(int page, int limit);
	InvoiceDto getInvoiceByInvoiceId(String invoiceId);
	InvoiceDto getContentByInvoiceId(String invoiceId);
	List<InvoiceDto> getInvoicesUser(String user);
}
