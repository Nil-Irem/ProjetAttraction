package metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Commodite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	@Column(name="prix_acquisition")
	private double prixAcquisition;
	private double taille;
	
	
	public Commodite(int id, String nom, double prixAcquisition, double taille) {
		this.id = id;
		this.nom = nom;
		this.prixAcquisition = prixAcquisition;
		this.taille = taille;
	}
	
	
	
	public Commodite(String nom, double prixAcquisition, double taille) {
		this.nom = nom;
		this.prixAcquisition = prixAcquisition;
		this.taille = taille;
	}
	
	public Commodite(){}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
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
