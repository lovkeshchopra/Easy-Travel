package com.easytravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.easytravel.entity.AreaInfoEntity;

public interface AreaInfoRepository extends JpaRepository<AreaInfoEntity, Long> {

	AreaInfoEntity findByStateAndCityAndArea(String state, String city, String area);
	
	@Query("select distinct obj.area from AreaInfoEntity obj where obj.city = ?1")
	List<String> getAreas(String city);

	List<String> findDistinctAreaByCity(String city);

}
