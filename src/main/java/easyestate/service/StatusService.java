package easyestate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easyestate.model.Status;
import easyestate.repository.StatusRepository;

@Service
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepository;
	
	public List<Status> findAll() {
		
		return statusRepository.findAll();
		
	}
	
	public Optional<Status> findById(Long id) {
		
		return statusRepository.findById(id);
		
	}
	
	public void save(Status status) {
		
		statusRepository.save(status);
		
	}
	
	public void deleteById(Long id) {
		
		statusRepository.deleteById(id);
		
	}

}