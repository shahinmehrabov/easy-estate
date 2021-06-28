package easyestate.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private User user;

	public MyUserDetails(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<Role> roles = user.getRoles();
		
		return mapRolesToAuthorities(roles);
		
	}
	
	private Set<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
		
	}
	
	public Long getId() {
		return user.getId();
	}
	
	public String getNumber() {
		return user.getNumber();
	}
	
	public void setNumber(String number) {
		user.setNumber(number);
	}
	
	public String getName() {
		return user.getName();
	}
	
	public void setName(String name) {
		user.setName(name);
	}
	
	public String getSurname() {
		return user.getSurname();
	}
	
	public void setSurname(String surname) {
		user.setSurname(surname);
	}
	
	public String getEmail() {
		return user.getEmail();
	}
	
	public void setEmail(String email) {
		user.setEmail(email);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	public void setPassword(String password) {
		user.setPassword(password);
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	public List<Property> getProperties() {
		return user.getProperties();
	}
	
	public void setProperties(List<Property> properties) {
		user.setProperties(properties);
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}