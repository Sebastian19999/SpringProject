package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Contact;
import com.example.demo.entities.InternetTraffic;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer>{
	@Query(value="select * from cdr_contact e where e.appellant_number like %:keyword%", nativeQuery=true)
	List<Contact> findByNumber(String keyword);
}
