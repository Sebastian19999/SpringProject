package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.InternetTraffic;
import com.example.demo.repositories.InternetTrafficRepository;

@Service
public class UserService {

	
	@Autowired
	private InternetTrafficRepository internetTrafficRepository;
	
	public String getUser() {
		Object user= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String[] sir=user.toString().split(" ");
		return sir[2].substring(0, sir[2].length()-1);
	}
	
	public InternetTraffic saveUser(InternetTraffic user) {
		return internetTrafficRepository.save(user);
	}
	
	public List<InternetTraffic> findByNumber(String keyword){
		return internetTrafficRepository.findByNumber(keyword);
	}
	
}
