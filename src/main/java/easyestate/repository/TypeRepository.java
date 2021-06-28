package easyestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import easyestate.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

}