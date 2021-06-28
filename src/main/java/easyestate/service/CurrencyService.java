package easyestate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import easyestate.model.Currency;
import easyestate.repository.CurrencyRepository;

@Repository
public class CurrencyService {
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	public List<Currency> findAll() {
		
		return currencyRepository.findAll();
		
	}
	
	public Optional<Currency> findById(Long id) {
		
		return currencyRepository.findById(id);
		
	}
	
	public void save(Currency currency) {
		
		currencyRepository.save(currency);
		
	}
	
	public void deleteById(Long id) {
		
		currencyRepository.deleteById(id);
		
	}

}