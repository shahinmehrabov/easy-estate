package easyestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import easyestate.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}