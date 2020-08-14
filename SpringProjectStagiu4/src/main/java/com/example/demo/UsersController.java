package com.example.demo;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controllers.DateClientController;
import com.example.demo.entities.SmsContact;
import com.example.demo.services.ClientServiceForBrowser;
import com.example.demo.services.SmsContactService;

@RestController
@CrossOrigin
public class UsersController {

	   @Autowired
	    private ClientServiceForBrowser clientServiceForBrowser;
	   
	   @Autowired
	   private SmsContactService smsContactService;
	
    @GetMapping("/registration/{userName}")
    public ResponseEntity<Void> register(@PathVariable String userName) {
        System.out.println("handling register user request: " + userName);
        
        SmsContact sms=new SmsContact(DateClientController.numar_introdus.trim());
        sms.setReceiver_number_sms(userName.trim());
        if(userName.trim().substring(0, 3).equals(DateClientController.numar_introdus.trim().substring(0, 3)))
        	sms.setSameNetwork(true);
        else
        	sms.setSameNetwork(false);
        
        smsContactService.saveSmsContact(sms);
        	
        try {
            UserStorage.getInstance().setUser(userName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll() {
        return UserStorage.getInstance().getUsers();
    }
}
