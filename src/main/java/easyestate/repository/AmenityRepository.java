package easyestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import easyestate.model.Amenity;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {

}