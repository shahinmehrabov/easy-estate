package easyestate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easyestate.model.Country;
import easyestate.repository.CountryRepository;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> findAll() {
		
		return countryRepository.findAll();
		
	}
	
	public Optional<Country> findById(Long id) {
		
		return countryRepository.findById(id);
		
	}
	
	public void save(Country country) {
		
		countryRepository.save(country);
		
	}
	
	public void deleteById(Long id) {
		
		countryRepository.deleteById(id);
		
	}

}