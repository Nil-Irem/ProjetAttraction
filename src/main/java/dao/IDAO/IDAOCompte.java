package dao.IDAO;

import java.util.List;

import metier.Compte;

public interface IDAOCompte extends IDAO<Compte, Integer>{
	
	public Compte seConnecter(String login, String password);
	public boolean findByLogin(String login);
	public List<Compte> filterCompte(String mot);
	
}
