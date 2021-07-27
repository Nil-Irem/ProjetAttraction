package metier;

import java.util.List;

public class Joueur extends Compte {
	
	private int id;
	private String nom;
	private List<Parc> parcs;


	public Joueur (String login, String password) {
		super(login,password);
		this.nom = login;
	}
	
	public Joueur (int id,String login, String password) {
		super(login,password);
		this.id = id;
		this.nom = login;
	}
	
	
	public Joueur (int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Le joueur " + nom + "(numero "+id+") possede " + parcs.size() + "parcs";
	}



	public double calculSolde()
	{
		return 0;
	}

}
