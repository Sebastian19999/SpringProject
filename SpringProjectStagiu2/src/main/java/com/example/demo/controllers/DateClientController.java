package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entities.Client;
import com.example.demo.entities.Contact;
import com.example.demo.entities.ExtraCharge;
import com.example.demo.entities.InternetTraffic;
import com.example.demo.entities.SmsContact;
import com.example.demo.entities.Subscription;
import com.example.demo.services.ClientServiceForBrowser;
import com.example.demo.services.ContactService;
import com.example.demo.services.SmsContactService;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("/cautareClient")
public class DateClientController {

	public static String numar_introdus;
	
	@Autowired
	private ClientServiceForBrowser clientService;
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private SmsContactService smsContactService;
	
	@Autowired UserService userService;
	
	@RequestMapping("/")
	public String getSearchForm() {
		return "cautare_dupa_numar";
	}
	
	@RequestMapping("/getClientByPhoneNumber")
	public String getAll(Model model,String keynumber) {
		List<Client> listClients=clientService.getAllClients();
		//model.addAttribute("listClients",listClients);
		
		String username="Sebastian";
		
		
		model.addAttribute("username",username);
		if(keynumber!=null) {
			model.addAttribute("listClients",clientService.findClientByPhoneNumber(keynumber));
		}else {
			model.addAttribute("listClients",listClients);
		}
		return "clients";
		//date_contact_cautat
	}
	
	/////////////////MESSENGER/////////
	@RequestMapping("/messenger")
	public String getSearchMessForm() {
		return "cautare_dupa_numar_mess";
	}
	
	@RequestMapping("/getOne")
	//@ResponseBody
	public String getOne(String keynumber) {
		System.out.println(clientService.findClientByPhoneNumber(keynumber).get(0).toString());
		
		numar_introdus=keynumber;
		
		//if(clientService.findClientByPhoneNumber(phone).get(0)!=null)
		return "redirect:/";
		//else
			//return "cautare_dupa_numar_mess";
	}
	
	
	
	////////DISPLAY DATA API//////////
	@RequestMapping("/search")
	public String getSearchFor() {
		return "date_client_cautare";
	}
	
	@RequestMapping("/getClient")
	//@ResponseBody
	public String getClientBySearchedNumber(Model model,String keynumber) {
		
		double total_seconds_in=0,total_seconds_out=0;
		int total_sms_in=0,total_sms_out=0;
		double total_traffic_seconds=0;
		
		List<Contact> listContacts=contactService.findByNumber(keynumber);
		List<SmsContact> listSmsContacts=smsContactService.findByNumber(keynumber);
		List<InternetTraffic> listTraffic=userService.findByNumber(keynumber);
		
		for(Contact contact : listContacts) {
			if(contact.isSameNetwork()==true)
				total_seconds_in+=((contact.getEnd_time()-contact.getStart_time())/1000);
			else
				total_seconds_out+=((contact.getEnd_time()-contact.getStart_time())/1000);
		}
		
		int minutes_in=(int) (total_seconds_in/60);
		int minutes_out=(int) (total_seconds_out/60);
		
		
		for(SmsContact contact : listSmsContacts) {
			if(contact.isSameNetwork()==true)
				total_sms_in+=contact.getSms_number();
			else
				total_sms_out+=contact.getSms_number();
		}
		
		for(InternetTraffic contact : listTraffic) {
			total_traffic_seconds+=((contact.getEnd_time()-contact.getStart_time())/1000);
		}
		
		int minutes_traffic=(int) (total_traffic_seconds/60);
		
		
		model.addAttribute("minutes_in",minutes_in);
		model.addAttribute("minutes_out",minutes_out);
		model.addAttribute("total_sms_in",total_sms_in);
		model.addAttribute("total_sms_out",total_sms_out);
		model.addAttribute("minutes_traffic",minutes_traffic);
		
		
		System.out.println(minutes_in);
		System.out.println(minutes_out);
		System.out.println(total_sms_in);
		System.out.println(total_sms_out);
		System.out.println(minutes_traffic);

		
		Client client=clientService.findClientByPhoneNumber(keynumber).get(0);
		
		Subscription subscription=client.getSubscription();
		ExtraCharge extraCharge=client.getExtraCharge();
		
		double monthly_cost=subscription.getMonthly_cost();
		
		double network_minutes_charge=0,minutes_charge=0;
		
		if(minutes_in>subscription.getNetwork_minutes_included()) {
			int var=0-(minutes_in-subscription.getNetwork_minutes_included());
			network_minutes_charge=Math.abs(var*extraCharge.getNetwork_call());
		}
		
		if(minutes_out>subscription.getMinutes_included()) {
			int var=0-(minutes_out-subscription.getMinutes_included());
			minutes_charge=Math.abs(var*extraCharge.getCall());
		}
		
		model.addAttribute("monthly_cost",monthly_cost);
		model.addAttribute("network_minutes_charge",network_minutes_charge);
		model.addAttribute("minutes_charge",minutes_charge);
		
		double network_sms_charge=0,sms_charge=0;
		
		if(total_sms_in>subscription.getNetwork_sms_included()) {
			int var=0-(total_sms_in-subscription.getNetwork_sms_included());
			network_sms_charge=Math.abs(var*extraCharge.getNetwork_sms());
		}
		
		if(total_sms_out>subscription.getSms_included()) {
			int var=0-(total_sms_out-subscription.getSms_included());
			sms_charge=Math.abs(var*extraCharge.getSms());
		}
		
		model.addAttribute("network_sms_charge",network_sms_charge);
		model.addAttribute("sms_charge",sms_charge);
		
		double traffic_charge=0;
		
		if(minutes_traffic>subscription.getTraffic_included()) {
			int var=(int) (0-(minutes_traffic-subscription.getTraffic_included()));
			traffic_charge=Math.abs(var*extraCharge.getInternet_traffic());
		}
		
		model.addAttribute("traffic_charge",traffic_charge);
		
		model.addAttribute("client",client);		
		
		
		numar_introdus=keynumber;
		
		//if(clientService.findClientByPhoneNumber(phone).get(0)!=null)
		return "client_data";
		//else
			//return "cautare_dupa_numar_mess";
	}
	
}
