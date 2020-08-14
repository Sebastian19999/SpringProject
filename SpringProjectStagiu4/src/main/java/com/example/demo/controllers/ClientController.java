package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Client;
import com.example.demo.entities.ExtraCharge;
import com.example.demo.entities.InternetTraffic;
import com.example.demo.entities.Subscription;
import com.example.demo.services.ClientServiceForBrowser;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("/clients")
public class ClientController {

	private Optional<Client> subF;
	
	private InternetTraffic newUser=null;
	
	private Optional<Client> getClient() {
		return subF;
	}
	
	private void setClient(Optional<Client> sub) {
		this.subF=sub;
	}
	
	@Autowired
	private ClientServiceForBrowser clientService;
	
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping("/getAll")
	public String getAll(Model model,String keyword) {
		List<Client> listClients=clientService.getAllClients();
		model.addAttribute("listClients",listClients);
		
		String username="Sebastian";
		
		
		model.addAttribute("username",username);
		if(keyword!=null) {
			model.addAttribute("listClients",clientService.findByKeyword(keyword));
		}else {
			model.addAttribute("listClients",listClients);
		}
		
		
		InternetTraffic user=new InternetTraffic(userService.getUser());
		user.setStart_time(System.currentTimeMillis());
		newUser=user;
		
		return "clients";
	}
	
	@RequestMapping("/getOne")
	@ResponseBody
	public Optional<Client> getOne(Integer id) {
		return clientService.getClient(id);
	}
	
	@PostMapping("/addNew")
	public String addNew(Client client) {
		clientService.addNew(client);
		return "redirect:/clients/getAll";
	}
	
	@RequestMapping(value="/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Client client) {
		clientService.update(client);
		return "redirect:/clients/getAll";
	}
	
	
	
	@RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})	
	public String delete(Integer id) {
		clientService.delete(id);
		return "redirect:/clients/getAll";
	}
	
	@RequestMapping("/subscriptions/{id}")
	public ModelAndView showSubscriptionClientPage(@PathVariable(name = "id") int id,Model model) {
	    ModelAndView mav = new ModelAndView("subscription_client");
	    Optional<Client> client = clientService.getClient(id);
	    
	    Subscription sub=client.get().getSubscription();
	    List<Subscription> listSubscriptions=new ArrayList<Subscription>();
	    listSubscriptions.add(sub);
	    
	    setClient(client);
	    mav.addObject("client", client);
	    model.addAttribute("listSubscriptions",listSubscriptions);
	    
	    
	     
	    return mav;
	}
	
	@RequestMapping("/charges/{id}")
	public ModelAndView showChargesClientPage(@PathVariable(name = "id") int id,Model model) {
	    ModelAndView mav = new ModelAndView("charges_client");
	    Optional<Client> client = clientService.getClient(id);
	    
	    ExtraCharge extra=client.get().getExtraCharge();
	    List<ExtraCharge> listCharges=new ArrayList<ExtraCharge>();
	    listCharges.add(extra);
	    
	    setClient(client);
	    mav.addObject("client", client);
	    model.addAttribute("listCharges",listCharges);
	    
	    
	     
	    return mav;
	}
	
	
	
	
	
	@RequestMapping(value = "/subscriptions/saveSub", method = RequestMethod.POST)
	//@ModelAttribute("client") Client client,
	public String saveSubscription(@ModelAttribute("subscription") Subscription subscription) {
	   
	    
	    //client.getComments().add(subscription);
		//getClient().getComments().add(subscription);
	     Client client=getClient().get();
	     client.setSubscription(subscription);
	     clientService.update(client);
		
	     return "redirect:/clients/getAll";
	}
	
	@RequestMapping("/editSub/{id}")
	public ModelAndView showEditSubscriptionPage(@PathVariable(name = "id") int id) {
		Optional<Client> client=getClient();
	    ModelAndView mav = new ModelAndView("edit_subscription");
	    Subscription subscription = client.get().getSubscription();
	    mav.addObject("subscription", subscription);
	     
	    return mav;
	}
	
	@RequestMapping("/exit")
	public String exitApp() {
		newUser.setEnd_time(System.currentTimeMillis());
		userService.saveUser(newUser);
		return "redirect:/logout";
	}
	
}
