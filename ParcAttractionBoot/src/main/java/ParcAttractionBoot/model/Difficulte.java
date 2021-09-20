package ParcAttractionBoot.model;

public enum Difficulte {

	Facile (1000000,10000),
	Moyen (750000,8000),
	Difficile (500000,5000);
	
	private double argent;
	private double tailleParc;
	
	
	private Difficulte(double argent,double tailleParc) {

		this.argent = argent;
		this.tailleParc = tailleParc;
	}
	
	
	public double getTailleParc() {
		return tailleParc;
	}

	public void setTailleParc(double tailleParc) {
		this.tailleParc = tailleParc;
	}

	public double getArgent() {
		return argent;
	}
	
	public void setArgent(double argent) {
		this.argent = argent;
	}
}
