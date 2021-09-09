package SoprAjc.ParcAttractionBoot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import SoprAjc.ParcAttractionBoot.model.Commodite;

public interface CommoditeRepository extends JpaRepository<Commodite, Integer> {

	List<Commodite> findByNomContaining(String mot);
}
