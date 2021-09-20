package ParcAttractionBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ParcAttractionBoot.model.Boutique;



public interface BoutiqueRepository extends JpaRepository<Boutique, Integer>{

	List<Boutique> findByNomContaining(String mot);

	List<Boutique> findByTailleBetween(double nombre1,double nombre2);
	List<Boutique> findByTailleGreaterThan(double nombre);
	List<Boutique> findByTailleLessThan(double nombre);
	
	List<Boutique> findByPrixAcquisitionBetween(double nombre1,double nombre2);
	List<Boutique> findByPrixAcquisitionGreaterThan(double nombre);
	List<Boutique> findByPrixAcquisitionLessThan(double nombre);
	
	List<Boutique> findByPrixFonctionnementBetween(double nombre1,double nombre2);
	List<Boutique> findByPrixFonctionnementGreaterThan(double nombre);
	List<Boutique> findByPrixFonctionnementLessThan(double nombre);
	
	List<Boutique> findByNbAmeliorationBetween(int nombre1,int nombre2);
	List<Boutique> findByNbAmeliorationGreaterThan(int nombre);
	List<Boutique> findByNbAmeliorationLessThan(int nombre);
	
	List<Boutique> findByTauxIncidentBetween(double nombre1,double nombre2);
	List<Boutique> findByTauxIncidentGreaterThan(double nombre);
	List<Boutique> findByTauxIncidentLessThan(double nombre);
	
	List<Boutique> findByAffluenceBetween(int nombre1,int nombre2);
	List<Boutique> findByAffluenceGreaterThan(int nombre);
	List<Boutique> findByAffluenceLessThan(int nombre);
	
	List<Boutique> findByRevenuJourPersonneBetween(double nombre1,double nombre2);
	List<Boutique> findByRevenuJourPersonneGreaterThan(double nombre);
	List<Boutique> findByRevenuJourPersonneLessThan(double nombre);

}
