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
		return "L'attraction " + nom + " (numero " + id+"), de taille " + taille + " m² a un prix d'acquisition de " + prixAcquisition
				+ "€, un prix quotidient de " + prixFonctionnement + "€, un niveau d'amelioration de " + niveauAmelioration
				+ " sur " + nbAmelioration + ", un taux d'incident de " + tauxIncident + "% et une affluence journalière de " + affluence;
	}
	
	

}
