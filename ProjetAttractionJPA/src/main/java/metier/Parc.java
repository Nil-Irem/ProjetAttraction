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

    
    public Parc(int id,String nom, double taille, int nbjour, double argent, Difficulte typeDifficulte,Joueur joueur)
    {
    	this.id=id;
		this.nomParc = nom;
		this.taille = taille;
		this.nbjour = nbjour;
		this.argent=argent;
		this.typeDifficulte=typeDifficulte;
		this.joueur=joueur;
    }
   

    public Parc (Joueur joueur,String nomParc,double taille, int nbjour,double argent,Difficulte typeDifficulte) {	
		this.joueur=joueur;
		this.nomParc = nomParc;
		this.taille = taille;
		this.nbjour = nbjour;
		this.argent=argent;
		this.typeDifficulte=typeDifficulte;
	}
			
	
	public Parc() {}
	
<<<<<<< Updated upstream:ProjetAttractionJPA/src/main/java/metier/Parc.java
=======
	
>>>>>>> Stashed changes:src/main/java/metier/Parc.java

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


	public int getNbjour() {
		return nbjour;
	}


	public void setNbjour(int nbjour) {
		this.nbjour = nbjour;
	}
	

	public Joueur getJoueur() {
		return joueur;
	}


	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}


	@Override
	public String toString() {
		return "Le parc "+ nomParc +" (id "+ id + "--> Difficult??: " +typeDifficulte+")\n" + "Taille: "+taille+"m?? \n\t | Jour de jeu --> " +nbjour+ " jours" + " | Argent --> "+argent+"\n" ;
	}

}
