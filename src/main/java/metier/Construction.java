package metier;

public class Construction {
	
	protected int id;
	protected String nom;
	protected double prixAcquisition;
	protected double prixFonctionnement;
	protected int niveauAmelioration;
	protected int nbAmelioration;
	protected double tauxIncident;
	protected double taille;
	protected int affluence;
	
	
	public Construction(int id, String nom, double prixAcquisition, double prixFonctionnement, int niveauAmelioration,
			int nbAmelioration, double tauxIncident, double taille, int affluence) {
		this.id = id;
		this.nom = nom;
		this.prixAcquisition = prixAcquisition;
		this.prixFonctionnement = prixFonctionnement;
		this.niveauAmelioration = niveauAmelioration;
		this.nbAmelioration = nbAmelioration;
		this.tauxIncident = tauxIncident;
		this.taille = taille;
		this.affluence = affluence;
	}
	
	public Construction(int id, String nom, double prixAcquisition, double prixFonctionnement,
			int nbAmelioration, double tauxIncident, double taille, int affluence) {
		this.id = id;
		this.nom = nom;
		this.prixAcquisition = prixAcquisition;
		this.prixFonctionnement = prixFonctionnement;
		this.nbAmelioration = nbAmelioration;
		this.tauxIncident = tauxIncident;
		this.taille = taille;
		this.affluence = affluence;
	}
	
	public Construction(String nom, double prixAcquisition, double prixFonctionnement, int niveauAmelioration,
			int nbAmelioration, double tauxIncident, double taille, int affluence) {
		this.nom = nom;
		this.prixAcquisition = prixAcquisition;
		this.prixFonctionnement = prixFonctionnement;
		this.niveauAmelioration = niveauAmelioration;
		this.nbAmelioration = nbAmelioration;
		this.tauxIncident = tauxIncident;
		this.taille = taille;
		this.affluence = affluence;
	}


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


	public double getPrixFonctionnement() {
		return prixFonctionnement;
	}


	public void setPrixFonctionnement(double prixFonctionnement) {
		this.prixFonctionnement = prixFonctionnement;
	}


	public int getNiveauAmelioration() {
		return niveauAmelioration;
	}


	public void setNiveauAmelioration(int niveauAmelioration) {
		this.niveauAmelioration = niveauAmelioration;
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
				+ ", prixFonctionnement=" + prixFonctionnement + ", niveauAmelioration=" + niveauAmelioration
				+ ", nbAmelioration=" + nbAmelioration + ", tauxIncident=" + tauxIncident + ", taille=" + taille
				+ ", affluence=" + affluence + "]";
	}
	
	
	

}
