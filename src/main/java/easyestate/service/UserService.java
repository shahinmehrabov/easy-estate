package easyestate.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import easyestate.dto.UserRegistrationDTO;
import easyestate.model.User;
import easyestate.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			
			throw new UsernameNotFoundException("Invalid username or password");
			
		}
		
		return user;
		
	}
	
	public Optional<User> findById(Long id) {
		
		return userRepository.findById(id);
		
	}
	
	public void save(UserRegistrationDTO userDTO) {
		
		User user = new User();
		
		user.setUsername(userDTO.getUsername());
		user.setNumber(userDTO.getNumber());
		user.setPassword(userDTO.getPassword());
		user.setName(userDTO.getName());
		user.setSurname(userDTO.getSurname());
		user.setEmail(userDTO.getEmail());
		user.setProfileImage(userDTO.getProfileImage());
		
		User user1 = loadUserByUsername(user.getUsername());
		
		user.setId(user1.getId());
		user.setRoles(user1.getRoles());
		
		userRepository.save(user);
		
	}
	
	public UserRegistrationDTO userToUserRegistrationDTO(User user) {
		
		UserRegistrationDTO userDTO = new UserRegistrationDTO();
		
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setNumber(user.getNumber());
		userDTO.setPassword(user.getPassword());
		userDTO.setName(user.getName());
		userDTO.setSurname(user.getSurname());
		userDTO.setEmail(user.getEmail());
		userDTO.setPassword(user.getPassword());
		userDTO.setRoles(user.getRoles());
		userDTO.setProfileImage(user.getProfileImage());
		
		return userDTO;
		
	}
}