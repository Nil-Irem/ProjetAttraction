package metier;

public class Admin extends Compte {
 

	public Admin(String login, String password) {
		super(login,password);
	}   

	
	@Override
	public String toString() {
		return "Admin [login=" + login + ", password=" + password + "]";
	}
	
}
