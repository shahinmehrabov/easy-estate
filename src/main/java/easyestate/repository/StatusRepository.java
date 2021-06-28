package easyestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import easyestate.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}