package ParcAttractionBoot.model;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("joueur")
public class Joueur extends Compte {

	public Joueur (String login, String password) {
		super(login,password);
		
	}
	
	public Joueur (Integer id,String login, String password) {
		super(id,login,password);		
	}
	
	
	public Joueur (Integer id) {
		super();
		this.id = id;
	}
	
	
	public Joueur () {
		super();
	}


	@Override
	public String toString() {
		return "Le joueur numero "+id+") possede ";
	}


}
