package ParcAttractionBoot.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Commodite extends Element {

	@JsonView(JsonViews.Common.class)
	private String nom;
	@Column(name="prix_acquisition")
	@JsonView(JsonViews.Common.class)
	private double prixAcquisition;
	@JsonView(JsonViews.Common.class)
	private double taille;
	
	
	public Commodite(Integer id, String nom, double prixAcquisition, double taille) {
		super(id);
		this.nom = nom;
		this.prixAcquisition = prixAcquisition;
		this.taille = taille;
	}
	
	
	
	public Commodite(String nom, double prixAcquisition, double taille) {
		super();
		this.nom = nom;
		this.prixAcquisition = prixAcquisition;
		this.taille = taille;
	}
	
	public Commodite(){}
	
	
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
	
	public double getTaille() {
		return taille;
	}
	public void setTaille(double taille) {
		this.taille = taille;
	}
	
	
	@Override
	public String toString() {
		return "La commodité" + nom + " (id--> " + id+")\n"+"\t|Taille: " + taille + " m²"+"\t|Prix d'acquisition:" + prixAcquisition + "€\n";
	}
	
	
	
}
