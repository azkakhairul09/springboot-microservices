package com.school.administration.app.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.administration.app.ui.io.entity.TokenEntity;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long>{
	TokenEntity findTokenByUserId(String userId);
	TokenEntity findUserIdByUserId(String userId);
	TokenEntity findByUserId(String userId);
}
