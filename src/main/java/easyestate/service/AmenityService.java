package easyestate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easyestate.model.Amenity;
import easyestate.repository.AmenityRepository;

@Service
public class AmenityService {
	
	@Autowired
	private AmenityRepository amenityRepository;
	
	public List<Amenity> findAll() {
		
		return amenityRepository.findAll();
		
	}
	
	public Optional<Amenity> findById(Long id) {
		
		return amenityRepository.findById(id);
		
	}
	
	public void save(Amenity amenity) {
		
		amenityRepository.save(amenity);
		
	}
	
	public void deleteById(Long id) {
		
		amenityRepository.deleteById(id);
		
	}

}