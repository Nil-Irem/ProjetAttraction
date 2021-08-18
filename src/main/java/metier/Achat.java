package metier;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Achat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	 @OneToMany
	 private List<Boutique> boutiques=new ArrayList();
	
	 @OneToMany	
	 private List<Attraction> attractions=new ArrayList();
	 
	 @OneToMany	
	 private List<Restaurant> restaurants=new ArrayList();
	    
	 @OneToMany
	 private List<Employe> employes=new ArrayList();
	 
	 @OneToMany
	 private List<Commodite> commodites=new ArrayList();


 public Achat(int id, List<Boutique> boutiques, List<Attraction> attractions,
			List<Restaurant> restaurants, List<Employe> employes, List<Commodite> commodites) {
		this.id = id;
		this.boutiques = boutiques;
		this.attractions = attractions;
		this.restaurants = restaurants;
		this.employes = employes;
		this.commodites = commodites;
	}
	
 public Achat(List<Boutique> boutiques, List<Attraction> attractions,
			List<Restaurant> restaurants, List<Employe> employes, List<Commodite> commodites) {
		this.boutiques = boutiques;
		this.attractions = attractions;
		this.restaurants = restaurants;
		this.employes = employes;
		this.commodites = commodites;
	}
	
 public Achat() {}


	public List<Boutique> getBoutiques() {
		return boutiques;
	}


	public void setBoutiques(List<Boutique> boutiques) {
		this.boutiques = boutiques;
	}


	public void newBoutique(Boutique boutique) {
		this.boutiques.add(boutique);
	}


	public List<Attraction> getAttractions() {
		return attractions;
	}


	public void setAttractions(List<Attraction> attractions) {
		this.attractions = attractions;
	}


	public void newAttraction(Attraction attraction) {
		this.attractions.add(attraction);
	}



	public List<Restaurant> getRestaurants() {
		return restaurants;
	}


	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}


	public void newRestaurant(Restaurant restaurant) {
		this.restaurants.add(restaurant);
	}



	public List<Employe> getEmployes() {
		return employes;
	}


	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}


	public void newEmploye(Employe employe) {
		this.employes.add(employe);
	}


	public List<Commodite> getCommodites() {
		return commodites;
	}




	public void setCommodites(List<Commodite> commodites) {
		this.commodites = commodites;
	}



	public void newCommodite(Commodite commodite) {
		this.commodites.add(commodite);
	}
	 
}
