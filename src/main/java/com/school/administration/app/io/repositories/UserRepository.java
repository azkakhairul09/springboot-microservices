package com.school.administration.app.io.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.administration.app.ui.io.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUsername(String username);
	UserEntity findByEmail(String email);
	UserEntity findByFullName(String fullName);
	UserEntity findUserByUserId(String userId);
	List<UserEntity> findAll();
	UserEntity findAddressByUserId(String userId);
	UserEntity findTokenByUserId(String userId);
	UserEntity findTokenByUsername(String username);
	UserEntity findRoleByUserId(String userId);
}
