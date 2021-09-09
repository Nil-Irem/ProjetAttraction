package SoprAjc.ParcAttractionBoot.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends Compte {
 
	public Admin(Integer id,String login, String password) {
		super(id,login,password);
	}   

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
