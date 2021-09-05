package dao.IDAO;

import java.util.List;

import metier.Restaurant;

public interface IDAORestaurant extends IDAO<Restaurant, Integer> {

	public List<Restaurant> filterRestaurant(String mot);
}
