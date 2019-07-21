package com.easytravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easytravel.entity.LoginEntity;
import com.easytravel.responseDTO.LoginDTO;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

	void save(LoginDTO request);

	boolean existsByMobileNo(String number);
	
	LoginEntity findByMobileNo(String mobileNo);
}
