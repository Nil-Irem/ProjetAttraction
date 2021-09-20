package ParcAttractionBoot.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public abstract class Construction extends Element{

	@JsonView(JsonViews.Common.class)
	protected String nom;
	@JsonView(JsonViews.Common.class)
	protected double taille;
	@Column(name="prix_acquisition")
	@JsonView(JsonViews.Common.class)
	protected double prixAcquisition;
	@Column(name="prix_fonctionnement")
	@JsonView(JsonViews.Common.class)
	protected double prixFonctionnement;
	@Column(name="nb_amelioration") 
	@JsonView(JsonViews.Common.class)
	protected int nbAmelioration;
	@Column(name="taux_incident")
	@JsonView(JsonViews.Common.class)
	protected double tauxIncident;
	@Column(name="affluence_max")
	@JsonView(JsonViews.Common.class)
	protected int affluence;
	
	
	public Construction(Integer id, String nom, double prixAcquisition, double prixFonctionnement,
			int nbAmelioration, double tauxIncident, double taille, int affluence) {
		super(id);
		this.nom = nom;
		this.prixAcquisition = prixAcquisition;
		this.prixFonctionnement = prixFonctionnement;
		this.nbAmelioration = nbAmelioration;
		this.tauxIncident = tauxIncident;
		this.taille = taille;
		this.affluence = affluence;
	}
	
	
	public Construction(String nom, double prixAcquisition, double prixFonctionnement,
			int nbAmelioration, double tauxIncident, double taille, int affluence) {
		super();
		this.nom = nom;
		this.prixAcquisition = prixAcquisition;
		this.prixFonctionnement = prixFonctionnement;
		this.nbAmelioration = nbAmelioration;
		this.tauxIncident = tauxIncident;
		this.taille = taille;
		this.affluence = affluence;
	}
	
	public Construction() {}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public double getPrixAcquisition() {
		return prixAcquisition;
	}


	public void setPrixAcquisition(double prixAcquisition) {
		this.prixAcquisition = prixAcquisition;
	}


	public double getPrixFonctionnement() {
		return prixFonctionnement;
	}


	public void setPrixFonctionnement(double prixFonctionnement) {
		this.prixFonctionnement = prixFonctionnement;
	}



	public int getNbAmelioration() {
		return nbAmelioration;
	}


	public void setNbAmelioration(int nbAmelioration) {
		this.nbAmelioration = nbAmelioration;
	}


	public double getTauxIncident() {
		return tauxIncident;
	}


	public void setTauxIncident(double tauxIncident) {
		this.tauxIncident = tauxIncident;
	}


	public double getTaille() {
		return taille;
	}


	public void setTaille(double taille) {
		this.taille = taille;
	}


	public int getAffluence() {
		return affluence;
	}


	public void setAffluence(int affluence) {
		this.affluence = affluence;
	}

	@Override
	public String toString() {
		return "Construction [id=" + id + ", nom=" + nom + ", prixAcquisition=" + prixAcquisition
				+ ", prixFonctionnement=" + prixFonctionnement
				+ ", nbAmelioration=" + nbAmelioration + ", tauxIncident=" + tauxIncident + ", taille=" + taille
				+ ", affluence=" + affluence + "]";
	}
	
	
	

}
