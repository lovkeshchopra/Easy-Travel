package com.easytravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easytravel.entity.RegisterEntity;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, Long> {

	Boolean existsByEmailId(String emailId);

}
