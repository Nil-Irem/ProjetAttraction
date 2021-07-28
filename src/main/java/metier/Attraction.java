package metier;

public class Attraction extends Construction {
	
	
	public Attraction(String nom, double prixAcquisition, double prixFonctionnement, int niveauAmelioration,
			int nbAmelioration, double tauxIncident, double taille, int affluence) {
		super(nom, prixAcquisition, prixFonctionnement, niveauAmelioration,nbAmelioration, tauxIncident, taille, affluence);
	}
	
	
	public Attraction(int id,String nom, double prixAcquisition, double prixFonctionnement,
			int nbAmelioration, double tauxIncident, double taille, int affluence) {
		super(id,nom, prixAcquisition, prixFonctionnement, nbAmelioration, tauxIncident, taille, affluence);
	}
	
	
	public Attraction(int id,String nom, double prixAcquisition, double prixFonctionnement, int niveauAmelioration,
			int nbAmelioration, double tauxIncident, double taille, int affluence) {
		super(id,nom, prixAcquisition, prixFonctionnement, niveauAmelioration,nbAmelioration, tauxIncident, taille, affluence);
	}



	@Override
	public String toString() {
		return "L'attraction " + nom + " (id--> " + id+")\n"+"\t|Taille: " + taille + " m²"+" \t|Prix d'acquisition: " + prixAcquisition
				+ "€"+" \t|Prix quotidient: " + prixFonctionnement + "€ \n\t|Niveau d'amelioration: " + niveauAmelioration
				+ " sur " + nbAmelioration + " \t|Taux d'incident: " + tauxIncident + "% \t|Affluence journalière: " + affluence+"\n";
	}
	
	

}
