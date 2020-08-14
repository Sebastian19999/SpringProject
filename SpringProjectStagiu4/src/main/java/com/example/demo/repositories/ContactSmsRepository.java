package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Client;
import com.example.demo.entities.Contact;
import com.example.demo.entities.SmsContact;

@Repository
public interface ContactSmsRepository extends JpaRepository<SmsContact,Integer>{
	@Query(value="select * from cdr_sms e where e.appellant_number_sms like %:keyword1% and e.receiver_number_sms like %:keyword2%", nativeQuery=true)
	List<SmsContact> findSmsContactByPhoneNumbers(String keyword1,String keyword2);
	
	@Query(value="select * from cdr_sms e where e.appellant_number_sms like %:keyword%", nativeQuery=true)
	List<SmsContact> findByNumber(String keyword);
}
