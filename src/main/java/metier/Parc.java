package metier;

import java.util.ArrayList;
import java.util.List;

public class Parc {

    private int id;
    private String nomParc;
    private List<Boutique> boutiques=new ArrayList();
    private List<Attraction> attractions=new ArrayList();
    private List<Restaurant> restaurants=new ArrayList();
    private List<Employe> employes=new ArrayList();
    private List<Commodite> commodites=new ArrayList();
    private double taille;
    private int nbjour;
	private double argent;
	private Difficulte typeDifficulte;
	
    
    public Parc(int id, String nomParc, List<Boutique> boutiques, List<Attraction> attractions,
			List<Restaurant> restaurants, List<Employe> employes, List<Commodite> commodites,double taille, int nbjour,double argent,Difficulte typeDifficulte) {
		this.id = id;
		this.nomParc = nomParc;
		this.boutiques = boutiques;
		this.attractions = attractions;
		this.restaurants = restaurants;
		this.employes = employes;
		this.commodites = commodites;
		this.taille = taille;
		this.nbjour = nbjour;
		this.argent=argent;
		this.typeDifficulte=typeDifficulte;
	}
    
    //constructeur pour find by id dans DAOParc
    public Parc(int id,String nom, double taille, int nbjour, double argent, Difficulte typeDifficulte)
    {
    	this.id=id;
		this.nomParc = nom;
		this.taille = taille;
		this.nbjour = nbjour;
		this.argent=argent;
		this.typeDifficulte=typeDifficulte;
    }
    
			
	

	//Constructeur pour creation d'un nouveau parc debut de partie
    public Parc(String nomParc,double taille, int nbjour,double argent,Difficulte typeDifficulte) {	
		
		this.nomParc = nomParc;
		this.taille = taille;
		this.nbjour = nbjour;
		this.argent=argent;
		this.typeDifficulte=typeDifficulte;
	}
	
	
	
	

	public double getArgent() {
		return argent;
	}



	public void setArgent(double argent) {
		this.argent = argent;
	}



	public Difficulte getTypeDifficulte() {
		return typeDifficulte;
	}



	public void setTypeDifficulte(Difficulte typeDifficulte) {
		this.typeDifficulte = typeDifficulte;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNomParc() {
		return nomParc;
	}


	public void setNomParc(String nomParc) {
		this.nomParc = nomParc;
	}


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

	public double getTaille() {
		return taille;
	}


	public void setTaille(double taille) {
		this.taille = taille;
	}


	public int getNbjour() {
		return nbjour;
	}


	public void setNbjour(int nbjour) {
		this.nbjour = nbjour;
	}


	@Override
	public String toString() {
		return "Le parc "+ nomParc +" (numero "+ id + "), de taille "+taille+"m², possede " + boutiques.size() + " magasins, " + attractions.size()
				+ " attractions, " + restaurants.size() + "restaurants, " + employes.size() + "employes et "+commodites+"commodites. Ce parc existe depuis " +nbjour+ " jours" + ". Le parc possède une somme de "+argent+". Son type de difficulté est "+typeDifficulte;
	}


}
