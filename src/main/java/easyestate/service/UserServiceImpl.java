package easyestate.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import easyestate.dto.UserRegistrationDTO;
import easyestate.model.MyUserDetails;
import easyestate.model.Property;
import easyestate.model.Role;
import easyestate.model.User;
import easyestate.repository.UserRepository;
import easyestate.repository.UserServiceRepository;

@Service
public class UserServiceImpl implements UserServiceRepository {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User save(UserRegistrationDTO registrationDTO) {
		
		List<Property> properties = new ArrayList<>();
		Set<Role> roles = new HashSet<>(Arrays.asList(new Role("ROLE_USER")));
		
		User user = new User(registrationDTO.getUsername(), registrationDTO.getNumber(), registrationDTO.getName(),
				registrationDTO.getSurname(), registrationDTO.getEmail(),
				passwordEncoder.encode(registrationDTO.getPassword()), properties,
				roles, "default.jpg");
		
		return userRepository.save(user);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			
			throw new UsernameNotFoundException("Invalid username or password");
			
		}
		
		return new MyUserDetails(user);
		
	}

}