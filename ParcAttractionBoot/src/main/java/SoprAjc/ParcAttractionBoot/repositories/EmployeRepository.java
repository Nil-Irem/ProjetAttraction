package SoprAjc.ParcAttractionBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import  SoprAjc.ParcAttractionBoot.model.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Integer>{

	List<Employe> findByMetierContaining(String mot);
}
