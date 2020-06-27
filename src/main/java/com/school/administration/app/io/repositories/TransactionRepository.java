package com.school.administration.app.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.school.administration.app.ui.io.entity.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
	TransactionEntity findByTransactionId(String transactionId);
	TransactionEntity findByUserId(String userId);
}
