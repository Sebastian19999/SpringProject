package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entities.Contact;
import com.example.demo.services.ContactService;

@Controller
@RequestMapping("/generareConexiune")
public class GenerareController {
	
	private Contact contactM;
	

	
	private void setContact(Contact _contact) {
		this.contactM=_contact;
	}
	
	private Contact getContact() {
		return contactM;
	}

	@Autowired
	private ContactService contactService;
	
	@RequestMapping("/getAll")
	public String getAll(Model model,String keyword) {
		Contact contact=new Contact();
		model.addAttribute("contact",contact);
		return "new_contact";
	}
	
	/*@RequestMapping("/newContact")
	public String showNewClientForm(Model model) {
		Contact contact=new Contact();
		model.addAttribute("contact",contact);
		return "new_contact";
	}*/
	
	@RequestMapping("/saveContact")
	//, method = RequestMethod.POST)
	public String saveClient(@ModelAttribute("contact") Contact contact) {
	
		contact.setStart_time(System.currentTimeMillis());
		if(contact.getAppellant_number().substring(0, 3).equals(contact.getReceiver_number().substring(0, 3)))
			contact.setSameNetwork(true);
		else
			contact.setSameNetwork(false);
	    setContact(contact);
	     
	    return "asteptare_contact";
	}
	
	@RequestMapping(value = "/contactFinalizat", method = RequestMethod.POST)
	public String finalizareApel(@ModelAttribute("contact") Contact contact) {
		
		getContact().setEnd_time(System.currentTimeMillis());
		
		contactService.saveContact(getContact());
		setContact(null);
	     
	    return "redirect:/";
	}
	
}
