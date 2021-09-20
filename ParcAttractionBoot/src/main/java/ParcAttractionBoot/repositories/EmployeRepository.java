package ParcAttractionBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ParcAttractionBoot.model.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Integer>{

	List<Employe> findByMetierContaining(String mot);

	List<Employe> findBySalaireGreaterThan(double nombre);
	List<Employe> findBySalaireLessThan(double nombre);
	List<Employe> findBySalaireBetween(double nombre1,double nombre2);
}
