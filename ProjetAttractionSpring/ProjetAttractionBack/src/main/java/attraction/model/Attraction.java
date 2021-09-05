package attraction.model;

import javax.persistence.Entity;


@Entity
public class Attraction extends Construction {
	

	public Attraction(String nom, double prixAcquisition, double prixFonctionnement,
			int nbAmelioration, double tauxIncident, double taille, int affluence) {
		super(nom, prixAcquisition, prixFonctionnement, nbAmelioration, tauxIncident, taille, affluence);
	}
	

	public Attraction(Integer id,String nom, double prixAcquisition, double prixFonctionnement,
			int nbAmelioration, double tauxIncident, double taille, int affluence) {
		super(id,nom, prixAcquisition, prixFonctionnement, nbAmelioration, tauxIncident, taille, affluence);
	}
	
	
	public Attraction() {
		super();
	}



	@Override
	public String toString() {
		return "L'attraction " + nom + " (id--> " + id+")\n"+"\t|Taille: " + taille +" m²"+" \t|Prix d'acquisition: " + prixAcquisition
				+ "€"+" \t|Prix quotidient: " + prixFonctionnement + "€"
				+ " sur " + nbAmelioration + " \t|Taux d'incident: " + tauxIncident + "% \t|Affluence journalière: " + affluence+"\n";
	}
	
	

}
