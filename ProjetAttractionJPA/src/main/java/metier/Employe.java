package metier;

import javax.persistence.Entity;


@Entity
public class Employe extends Element{

	private String metier;
	private double salaire;
	
	
	public Employe(int id, String metier, double salaire) {
		super(id);
		this.metier = metier;
		this.salaire = salaire;
	}


	public Employe(String metier, double salaire) {
		super();
		this.metier = metier;
		this.salaire = salaire;
	}

	
	public Employe() {}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMetier() {
		return metier;
	}


	public void setMetier(String metier) {
		this.metier = metier;
	}


	public double getSalaire() {
		return salaire;
	}


	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}


	
	@Override
	public String toString() {
		return "L'employé " + metier + " (id--> " + id+")\n"+"\t|Salaire journalier: " + salaire+ "€\n";
		
	}	
}
