package dao.IDAO;

import java.util.List;

import metier.Achat;
import metier.Element;
import metier.Joueur;
import metier.Parc;

public interface IDAOParc extends IDAO<Parc, Integer> {

	public List<Parc> filterParc (String mot);
	public List<Parc> findByJoueur(Joueur joueur);

	//Return true si le nom existe deja pour le joueur
	public boolean checkSameParcName(String nomParc, Joueur joueur);

}