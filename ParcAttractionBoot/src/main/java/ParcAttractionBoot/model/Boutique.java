package ParcAttractionBoot.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Boutique extends Construction {

	@JsonView(JsonViews.Common.class)
	@Column(name="revenu_jour_personne")
	private double revenuJourPersonne;

	public Boutique(Integer id, String nom, double prixAcquisition, double prixFonctionnement,
			int nbAmelioration, double tauxIncident, double taille, int affluence,double revenuJourPersonne) {
		super(id,nom, prixAcquisition, prixFonctionnement,nbAmelioration, tauxIncident, taille, affluence);
		this.revenuJourPersonne = revenuJourPersonne;
	}
	
	
	public Boutique(String nom, double prixAcquisition, double prixFonctionnement,
			int nbAmelioration, double tauxIncident, double taille, int affluence,double revenuJourPersonne) {
		super(nom, prixAcquisition, prixFonctionnement,nbAmelioration, tauxIncident, taille, affluence);
		this.revenuJourPersonne = revenuJourPersonne;
	}
	
	
	public Boutique() {
		super();
	}
	

	public double getrevenuJourPersonne() {
		return revenuJourPersonne;
	}

	public void setrevenuJourPersonne(double revenuJourPersonne) {
		this.revenuJourPersonne = revenuJourPersonne;
	}	
	
	@Override
	public String toString() {
		return "Le magasin " + nom + " (id--> " + id+")\n"+"\t|Taille: " + taille + " m²"+"\t|Prix d'acquisition:" + prixAcquisition
				+ "€"+"\t|Prix quotidient: " + prixFonctionnement + "€"
				+ " sur " + nbAmelioration + "\t|Taux d'incident:" + tauxIncident + "%\t|Affluence journalière max: "
				+ affluence +" personnes"+"\n\t|Revenu journalier par personne: " + revenuJourPersonne + "€\n";
		
	}
	  
	  
}
