package easyestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import easyestate.dto.UserRegistrationDTO;
import easyestate.repository.UserServiceRepository;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	private UserServiceRepository userService;
	
	@ModelAttribute("userDTO")
	public UserRegistrationDTO userRegistrationDTO() {
		return new UserRegistrationDTO();
	}
	
	@GetMapping
	public String registration() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth == null || auth instanceof AnonymousAuthenticationToken) {
			
			return "registration";
			
		}
		
		return "redirect:/user/profile";
		
	}
	
	@PostMapping
	private String registration(@ModelAttribute("userDTO") UserRegistrationDTO registrationDTO) {
		
		userService.save(registrationDTO);
		
		return "redirect:/registration?success";
		
	}

}