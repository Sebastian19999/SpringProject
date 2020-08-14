package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Client;
import com.example.demo.entities.InternetTraffic;

@Repository
public interface InternetTrafficRepository extends JpaRepository<InternetTraffic,Integer>{

	@Query(value="select * from cdr_internet_traffic e where e.phone_number like %:keyword%", nativeQuery=true)
	List<InternetTraffic> findByNumber(String keyword);
	
}
