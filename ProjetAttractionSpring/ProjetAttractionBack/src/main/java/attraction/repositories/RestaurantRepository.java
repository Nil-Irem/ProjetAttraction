package attraction.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import attraction.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	
	List<Restaurant> findByNomContaining(String mot);
}
