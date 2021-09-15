package ParcAttractionBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ParcAttractionBoot.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	List<Restaurant> findByNomContaining(String mot);

	List<Restaurant> findByTailleBetween(double nombre1,double nombre2);
	List<Restaurant> findByTailleGreaterThan(double nombre);
	List<Restaurant> findByTailleLessThan(double nombre);
	
	List<Restaurant> findByPrixAcquisitionBetween(double nombre1,double nombre2);
	List<Restaurant> findByPrixAcquisitionGreaterThan(double nombre);
	List<Restaurant> findByPrixAcquisitionLessThan(double nombre);
	
	List<Restaurant> findByPrixFonctionnementBetween(double nombre1,double nombre2);
	List<Restaurant> findByPrixFonctionnementGreaterThan(double nombre);
	List<Restaurant> findByPrixFonctionnementLessThan(double nombre);
	
	List<Restaurant> findByNbAmeliorationBetween(int nombre1,int nombre2);
	List<Restaurant> findByNbAmeliorationGreaterThan(int nombre);
	List<Restaurant> findByNbAmeliorationLessThan(int nombre);
	
	List<Restaurant> findByTauxIncidentBetween(double nombre1,double nombre2);
	List<Restaurant> findByTauxIncidentGreaterThan(double nombre);
	List<Restaurant> findByTauxIncidentLessThan(double nombre);
	
	List<Restaurant> findByAffluenceBetween(int nombre1,int nombre2);
	List<Restaurant> findByAffluenceGreaterThan(int nombre);
	List<Restaurant> findByAffluenceLessThan(int nombre);
	
	List<Restaurant> findByRevenuJourPersonneBetween(double nombre1,double nombre2);
	List<Restaurant> findByRevenuJourPersonneGreaterThan(double nombre);
	List<Restaurant> findByRevenuJourPersonneLessThan(double nombre);
}
