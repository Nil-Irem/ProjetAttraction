package metier;

public abstract class Compte {
	
	protected String login;
	protected String password;
	
	
	
	
	public Compte(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public Compte(String login) {
		this.login = login;
		
	}
	
	
	public Compte() {
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Compte [login=" + login + ", password=" + password + "]";
	}

    public void seConnecter()
    {
        
    }
	

	

}
