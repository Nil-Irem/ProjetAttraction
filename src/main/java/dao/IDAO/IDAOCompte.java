package dao.IDAO;

import java.util.List;

import metier.Compte;

public interface IDAOCompte extends IDAO<Compte, Integer>{
	
	public Compte seConnecter(String login, String password);
	public List<Compte> filterCompte(String mot);

	//Return True si le login existe deja
	public boolean checkSameLogin(String login);	
}
