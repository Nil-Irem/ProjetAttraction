package ParcAttractionBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ParcAttractionBoot.model.Attraction;



public interface AttractionRepository extends JpaRepository<Attraction, Integer>{

	List<Attraction> findByNomContaining(String mot);
		
	List<Attraction> findByTailleBetween(double nombre1,double nombre2);
	List<Attraction> findByTailleGreaterThan(double nombre);
	List<Attraction> findByTailleLessThan(double nombre);
	
	List<Attraction> findByPrixAcquisitionBetween(double nombre1,double nombre2);
	List<Attraction> findByPrixAcquisitionGreaterThan(double nombre);
	List<Attraction> findByPrixAcquisitionLessThan(double nombre);
	
	List<Attraction> findByPrixFonctionnementBetween(double nombre1,double nombre2);
	List<Attraction> findByPrixFonctionnementGreaterThan(double nombre);
	List<Attraction> findByPrixFonctionnementLessThan(double nombre);
	
	List<Attraction> findByNbAmeliorationBetween(int nombre1,int nombre2);
	List<Attraction> findByNbAmeliorationGreaterThan(int nombre);
	List<Attraction> findByNbAmeliorationLessThan(int nombre);
	
	List<Attraction> findByTauxIncidentBetween(double nombre1,double nombre2);
	List<Attraction> findByTauxIncidentGreaterThan(double nombre);
	List<Attraction> findByTauxIncidentLessThan(double nombre);
	
	List<Attraction> findByAffluenceBetween(int nombre1,int nombre2);
	List<Attraction> findByAffluenceGreaterThan(int nombre);
	List<Attraction> findByAffluenceLessThan(int nombre);
}
