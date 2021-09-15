package ParcAttractionBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ParcAttractionBoot.model.Commodite;

public interface CommoditeRepository extends JpaRepository<Commodite, Integer> {

	List<Commodite> findByNomContaining(String mot);

	List<Commodite> findByPrixAcquisitionBetween(double nombre1,double nombre2);
	List<Commodite> findByPrixAcquisitionGreaterThan(double nombre);
	List<Commodite> findByPrixAcquisitionLessThan(double nombre);
	
	List<Commodite> findByTailleBetween(double nombre1,double nombre2);
	List<Commodite> findByTailleGreaterThan(double nombre);
	List<Commodite> findByTailleLessThan(double nombre);
}
