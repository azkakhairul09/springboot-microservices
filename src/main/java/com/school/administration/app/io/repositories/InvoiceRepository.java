package com.school.administration.app.io.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.administration.app.ui.io.entity.InvoiceEntity;
import com.school.administration.app.ui.io.entity.UserEntity;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

	List<InvoiceEntity> findInvoiceByCreatedDate(String newStopTime);
	InvoiceEntity findInvoiceByInvoiceId(String invoiceId);
	InvoiceEntity findContentByInvoiceId(String invoiceId);
	List<InvoiceEntity> findInvoiceByUserId(UserEntity userId);
	List<InvoiceEntity> findInvoiceByInvoiceDateAndIsExpired(String date, Boolean expired);
}
