package easyestate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easyestate.model.Type;
import easyestate.repository.TypeRepository;

@Service
public class TypeService {
	
	@Autowired
	private TypeRepository typeRepository;
	
	public List<Type> findAll() {
		
		return typeRepository.findAll();
		
	}
	
	public Optional<Type> findById(Long id) {
		
		return typeRepository.findById(id);
		
	}
	
	public void save(Type type) {
		
		 typeRepository.save(type);
		 
	}
	
	public void deleteById(Long id) {
		
		typeRepository.deleteById(id);
		
	}

}