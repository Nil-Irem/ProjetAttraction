package metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Employe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String metier;
	private double salaire;
	
	
	public Employe(int id, String metier, double salaire) {
		this.id = id;
		this.metier = metier;
		this.salaire = salaire;
	}


	public Employe(String metier, double salaire) {
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
