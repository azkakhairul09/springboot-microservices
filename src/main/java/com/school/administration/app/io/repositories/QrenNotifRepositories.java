package com.school.administration.app.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.administration.app.ui.io.entity.QrenNotifEntity;

@Repository
public interface QrenNotifRepositories extends JpaRepository<QrenNotifEntity, Long> {
}
