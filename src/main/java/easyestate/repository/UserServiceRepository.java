package easyestate.repository;

import org.springframework.security.core.userdetails.UserDetailsService;

import easyestate.dto.UserRegistrationDTO;
import easyestate.model.User;

public interface UserServiceRepository extends UserDetailsService {
	
	User save(UserRegistrationDTO registrationDTO);

}