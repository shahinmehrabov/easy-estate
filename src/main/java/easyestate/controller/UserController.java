package easyestate.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import easyestate.dto.PasswordDTO;
import easyestate.dto.UserRegistrationDTO;
import easyestate.model.User;
import easyestate.service.StorageService;
import easyestate.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/profile")
	public String profile(Principal principal, Model model) {
		
		String username = principal.getName();
		
		UserRegistrationDTO user = userService.userToUserRegistrationDTO(userService.loadUserByUsername(username));
		
		model.addAttribute("user", user);
		
		return "profile";
		
	}
	
	@GetMapping("/properties")
	public String properties(Principal principal, Model model) {
		
		String username = principal.getName();
		
		User user = userService.loadUserByUsername(username);
		
		UserRegistrationDTO userDTO = userService.userToUserRegistrationDTO(userService.loadUserByUsername(username));
		
		model.addAttribute("user", userDTO);
		
		model.addAttribute("properties", user.getProperties());
		
		return "my-properties";
		
	}
	
	@PostMapping("/profile")
	public String profile(Principal principal, @ModelAttribute("user") UserRegistrationDTO user,
			@RequestParam("profile_image") MultipartFile profileImage) throws IOException {
		
		if(!profileImage.isEmpty()) {
			
			String profileImageName = System.currentTimeMillis() + "-" + StringUtils.cleanPath(profileImage.getOriginalFilename());
			
			user.setProfileImage(profileImageName);
			
			storageService.uploadFile(profileImage, profileImageName);
			
		}
		
		userService.save(user);
		
		return "redirect:/user/profile?success";
		
	}
	
	@GetMapping("/password")
	public String password(Principal principal, Model model) {
		
		String username = principal.getName();
		
		UserRegistrationDTO userDTO = userService.userToUserRegistrationDTO(userService.loadUserByUsername(username));
		
		model.addAttribute("user", userDTO);
		
		PasswordDTO password = new PasswordDTO();
		
		model.addAttribute("password", password);
		
		return "change-password";
		
	}
	
	@PostMapping("/password")
	public String password(@ModelAttribute("password") PasswordDTO password, Principal principal) {
		
		String username = principal.getName();
		
		UserRegistrationDTO user = userService.userToUserRegistrationDTO(userService.loadUserByUsername(username));
		
		if(passwordEncoder.matches(password.getCurrentPassword(), user.getPassword())) {
			
			if(password.getNewPassword().equals(password.getMatchNewPassword())) {
				
				user.setPassword(passwordEncoder.encode(password.getNewPassword()));
				
				userService.save(user);
				
				return "redirect:/user/password?success";
				
			} else {
				
				return "redirect:/user/password?nomatchnew";
				
			}
			
		} else {
			
			return "redirect:/user/password?nomatchold";
			
		}
		
	}

}