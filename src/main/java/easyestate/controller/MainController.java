package easyestate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import easyestate.model.Property;
import easyestate.service.PropertyService;

@Controller
public class MainController {
	
	@Autowired
	private PropertyService propertyService;
	
	@GetMapping("/")
	public String home(Model model) {
		
		List<Property> properties = propertyService.findAll();
		
		model.addAttribute("properties", properties);
		
		return "all-properties";
		
	}
	
	@GetMapping("/login")
	public String login() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth == null || auth instanceof AnonymousAuthenticationToken) {
			
			return "login";
			
		}
		
		return "redirect:/user/profile";
		
	}

}