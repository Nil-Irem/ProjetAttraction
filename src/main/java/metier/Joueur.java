package metier;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("joueur")
public class Joueur extends Compte {

	public Joueur (String login, String password) {
		super(login,password);
		
	}
	
	public Joueur (int id,String login, String password) {
		super(id,login,password);		
	}
	
	
	public Joueur (int id) {
		super();
		this.id = id;
	}
	
	
	public Joueur () {
		super();
	}

	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Le joueur numero "+id+") possede ";
	}


}
