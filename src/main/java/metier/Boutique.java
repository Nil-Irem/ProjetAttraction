package metier;


public class Boutique extends Construction {

	  private double revenuJourPersonne;

	public Boutique(int id, String nom, double prixAcquisition, double prixFonctionnement, int niveauAmelioration,
			int nbAmelioration, double tauxIncident, double taille, int affluence,double revenuJourPersonne) {
		super(id,nom, prixAcquisition, prixFonctionnement, niveauAmelioration,nbAmelioration, tauxIncident, taille, affluence);
		this.revenuJourPersonne = revenuJourPersonne;
	}
	
	
	public Boutique(String nom, double prixAcquisition, double prixFonctionnement, int niveauAmelioration,
			int nbAmelioration, double tauxIncident, double taille, int affluence,double revenuJourPersonne) {
		super(nom, prixAcquisition, prixFonctionnement, niveauAmelioration,nbAmelioration, tauxIncident, taille, affluence);
		this.revenuJourPersonne = revenuJourPersonne;
	}
	
	

	public Boutique(int id, String nom, double prixAcquisition, double prixFonctionnement,
			int nbAmelioration, double tauxIncident, double taille, int affluence,double revenuJourPersonne) {
		super(id,nom, prixAcquisition, prixFonctionnement,nbAmelioration, tauxIncident, taille, affluence);
		this.revenuJourPersonne = revenuJourPersonne;
	}
	

	public double getrevenuJourPersonne() {
		return revenuJourPersonne;
	}

	public void setrevenuJourPersonne(double revenuJourPersonne) {
		this.revenuJourPersonne = revenuJourPersonne;
	}	
	
	@Override
	public String toString() {
		return "Le magasin " + nom + " (numero " + id+"), de taille " + taille + " m² a un prix d'acquisition de " + prixAcquisition
				+ "€, un prix quotidient de " + prixFonctionnement + "€, un niveau d'amelioration de " + niveauAmelioration
				+ " sur " + nbAmelioration + ", un taux d'incident de " + tauxIncident + "%, une affluence journalière maximum de "
				+ affluence + " personnes et un revenu journalier par personne de " + revenuJourPersonne + "€";
	}
	  
	  
}
