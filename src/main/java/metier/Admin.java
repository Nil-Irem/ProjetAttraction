package metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends Compte {
 

	public Admin(String login, String password) {
		super(login,password);
	}   
	
	public Admin() {
		super();
	}   

	
	@Override
	public String toString() {
		return "Admin [login=" + login + ", password=" + password + "]";
	}
	
}
