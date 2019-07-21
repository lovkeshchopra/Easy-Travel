package com.easytravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easytravel.entity.BusEntity;

@Repository
public interface BusRepository extends JpaRepository<BusEntity, Long> {

	BusEntity findBybusNumber(String busNumber);

	void findByBusNumber(String busNumber);

//	List<BusEntity> findBySourceAndDestination(String source, String destination);

	@Query("select distinct obj.morningTime ,obj.busNumber from BusEntity obj where obj.source = ?1 and obj.destination =?2")
	List<String> getSourceAndDestination(String source, String destination);

	List<BusEntity> findBySourceAndDestination(String source, String destination);

}
