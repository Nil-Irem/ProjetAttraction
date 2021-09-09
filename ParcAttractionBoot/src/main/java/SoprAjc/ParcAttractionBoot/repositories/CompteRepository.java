package SoprAjc.ParcAttractionBoot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import  SoprAjc.ParcAttractionBoot.model.*;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

	
	Optional<Compte> findByLoginAndPassword(String login,String password);
	
	Optional<Compte> findByLogin(String login);
	
	List<Compte> findByLoginContaining(String mot);
	
	
}
