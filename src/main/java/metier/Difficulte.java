package metier;

public enum Difficulte {

	Facile (1000000),
	Moyen (750000),
	Difficile (500000);
	
	private double argent;
	
	
	private Difficulte(double argent) {

		this.argent = argent;
	}
	
	
	

	
	
	public double getArgent() {
		return argent;
	}
	
	public void setArgent(double argent) {
		this.argent = argent;
	}
	
	
	
}
