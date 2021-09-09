package SoprAjc.ParcAttractionBoot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import  SoprAjc.ParcAttractionBoot.model.*;

public interface ParcRepository extends JpaRepository<Parc, Integer> {


	List<Parc> findByNomParcContaining (String mot);

	List<Parc> findByJoueur(Joueur joueur);

	Optional<Parc> findByNomParcAndJoueur(String nomParc, Joueur joueur);
}
