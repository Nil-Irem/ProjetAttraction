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
		return "Le magasin " + nom + " (id--> " + id+")\n"+"\t|Taille: " + taille + " m²"+"\t|Prix d'acquisition:" + prixAcquisition
				+ "€"+"\t|Prix quotidient: " + prixFonctionnement + "€\n\t|Niveau d'amelioration: " + niveauAmelioration
				+ " sur " + nbAmelioration + "\t|Taux d'incident:" + tauxIncident + "%\t|Affluence journalière max: "
				+ affluence +" personnes"+"\n\t|Revenu journalier par personne: " + revenuJourPersonne + "€\n";
		
	}
	  
	  
}
