package attraction.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import attraction.model.Attraction;

public interface AttractionRepository extends JpaRepository<Attraction, Integer>{

	List<Attraction> findByNomContaining(String mot);
}
