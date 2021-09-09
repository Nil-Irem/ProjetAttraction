package SoprAjc.ParcAttractionBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import SoprAjc.ParcAttractionBoot.model.Boutique;


public interface BoutiqueRepository extends JpaRepository<Boutique, Integer>{

	List<Boutique> findByNomContaining(String mot);
}
