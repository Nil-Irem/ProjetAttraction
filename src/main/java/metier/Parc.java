package metier;

import javax.persistence.*;

@Entity
public class Parc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="nom")
    private String nomParc;
    private double taille;
    private int nbjour;
	private double argent;
	@Enumerated(EnumType.STRING)
	private Difficulte typeDifficulte;
	@ManyToOne
	@JoinColumn(name="id_joueur")
	private Joueur joueur;
	@ManyToOne
	private Achat achats;
    
   
    
	
    //Constructeur avec id_joueur
    public Parc(Joueur joueur,String nomParc, Achat achats,double taille, int nbjour,double argent,Difficulte typeDifficulte, int id_joueur) {
		this.joueur = joueur;
		this.nomParc = nomParc;
		this.achats=achats;
		this.taille = taille;
		this.nbjour = nbjour;
		this.argent=argent;
		this.typeDifficulte=typeDifficulte;
	}
    
    
    public Parc(int id, String nomParc, Achat achats,double taille, int nbjour,double argent,Difficulte typeDifficulte) {
		this.id = id;
		this.nomParc = nomParc;
		this.achats=achats;
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
	
	
	public Parc() {}
	
	


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


	public double getTaille() {
		return taille;
	}


	public void setTaille(double taille) {
		this.taille = taille;
	}
	
	

	public Achat getAchat() {
		return achats;
	}


	public void setAchat(Achat achats) {
		this.achats = achats;
	}


	public int getNbjour() {
		return nbjour;
	}


	public void setNbjour(int nbjour) {
		this.nbjour = nbjour;
	}


	/*@Override
	public String toString() {
		return "Le parc "+ nomParc +" (id "+ id + "--> Difficulté: " +typeDifficulte+")\n" + "Taille: "+taille+"m²,\n\t| Boutiques--> " + boutiques.size() + " | Attractions --> " + attractions.size()
				+ "| Restaurants-->" + restaurants.size() + "\n\t| Employés --> " + employes.size() + " | Commodités --> "+commodites.size()+" | Jour de jeu --> " +nbjour+ " jours" + " | Argent --> "+argent+"\n" ;
	}
	*/
	
	@Override
	public String toString() {
		return "Le parc "+ nomParc +" (id "+ id + "--> Difficulté: " +typeDifficulte+")\n" + "Taille: "+taille+"m² \n\t | Jour de jeu --> " +nbjour+ " jours" + " | Argent --> "+argent+"\n" ;
	}

}
