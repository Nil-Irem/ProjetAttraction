package ParcAttractionBoot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ParcAttractionBoot.model.Difficulte;
import ParcAttractionBoot.model.Joueur;
import ParcAttractionBoot.model.Parc;

public interface ParcRepository extends JpaRepository<Parc, Integer> {

	Optional<Parc> findByNomParcAndJoueur(String nomParc, Joueur joueur);

	List<Parc> findByNomParcContaining (String mot);
	List<Parc> findByTypeDifficulte(Difficulte mot);
	List<Parc> findByJoueur(Joueur joueur);

	List<Parc> findByArgentLessThan(int nombre);
	List<Parc> findByArgentGreaterThan(int nombre);
	
	List<Parc> findByNbjourLessThan(int nombre);
	List<Parc> findByNbjourGreaterThan(int nombre);
	
	List<Parc> findByTailleLessThan(int nombre);
	List<Parc> findByTailleGreaterThan(int nombre);
}
