package easyestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import easyestate.model.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}