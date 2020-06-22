package com.school.administration.app.io.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.school.administration.app.ui.io.entity.ProductsEntity;

@Repository
public interface ProductsRepository extends PagingAndSortingRepository<ProductsEntity, Long>{
	Page<ProductsEntity> findAllByOrderByCreatedDateDesc(Pageable pageableRequest);
	ProductsEntity findProductByProductId(String productId);
	List<ProductsEntity> findProductByExpiredDate(String expiredDate);
	ProductsEntity findByProductName(String productName);
}
