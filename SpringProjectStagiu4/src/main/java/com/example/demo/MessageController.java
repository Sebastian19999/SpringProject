package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controllers.DateClientController;
import com.example.demo.entities.SmsContact;
import com.example.demo.services.SmsContactService;

@RestController
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @Autowired
    private SmsContactService smsContactService;
    
 

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageModel message) {
        System.out.println("handling send message: " + message + " to: " + to);
        boolean isExists = UserStorage.getInstance().getUsers().contains(to);
        if (isExists) {
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
            System.out.println("Numar introdus: "+DateClientController.numar_introdus);
            System.out.println("Catre: "+to);
            System.out.println("Mesaj: "+message);
            System.out.println("Conexiune: "+smsContactService.findSmsContactByPhoneNumbers(DateClientController.numar_introdus, to));
            SmsContact sms=smsContactService.findSmsContactByPhoneNumbers(DateClientController.numar_introdus, to).get(0);
            int nr = sms.getSms_number();
            nr++;
            sms.setSms_number(nr);
            
            smsContactService.updateSmsContact(sms);
        }
    }
}
